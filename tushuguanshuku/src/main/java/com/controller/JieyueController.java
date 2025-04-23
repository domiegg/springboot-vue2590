
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 借阅记录
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jieyue")
public class JieyueController {
    private static final Logger logger = LoggerFactory.getLogger(JieyueController.class);

    @Autowired
    private JieyueService jieyueService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private TushuService tushuService;
    @Autowired
    private YonghuService yonghuService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = jieyueService.queryPage(params);

        //字典表数据转换
        List<JieyueView> list =(List<JieyueView>)page.getList();
        for(JieyueView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JieyueEntity jieyue = jieyueService.selectById(id);
        if(jieyue !=null){
            //entity转view
            JieyueView view = new JieyueView();
            BeanUtils.copyProperties( jieyue , view );//把实体数据重构到view中

                //级联表
                TushuEntity tushu = tushuService.selectById(jieyue.getTushuId());
                if(tushu != null){
                    BeanUtils.copyProperties( tushu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setTushuId(tushu.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(jieyue.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JieyueEntity jieyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jieyue:{}",this.getClass().getName(),jieyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            jieyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JieyueEntity> queryWrapper = new EntityWrapper<JieyueEntity>()
            .eq("yonghu_id", jieyue.getYonghuId())
            .eq("tushu_id", jieyue.getTushuId())
            .eq("jieyue_types", jieyue.getJieyueTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JieyueEntity jieyueEntity = jieyueService.selectOne(queryWrapper);
        if(jieyueEntity==null){
            jieyue.setCreateTime(new Date());
            YonghuEntity yonghuEntity = yonghuService.selectById(jieyue.getYonghuId());
            if(yonghuEntity.getJieyueshuliang()<=0){
                return R.error("可借阅图书数量为0");
            }
            yonghuEntity.setJieyueshuliang(yonghuEntity.getJieyueshuliang() - 1);
            boolean b = yonghuService.updateById(yonghuEntity);
            if(!b){
                return R.error("修改用户数据报错");
            }
            TushuEntity tushuEntity = tushuService.selectById(jieyue.getTushuId());
            if(tushuEntity.getTushuKucunNumber()<=0){
                return R.error("图书数量为0不可借阅");
            }
            tushuEntity.setTushuKucunNumber(tushuEntity.getTushuKucunNumber() - 1);
            boolean b1 = tushuService.updateById(tushuEntity);
            if(!b1){
                return R.error("修改图书数据报错");
            }
            jieyue.setHuanshuTime(new Date(jieyue.getJieyueTime().getTime()+Long.valueOf(tushuEntity.getTushuJieyue())*24*60*60*1000));
            jieyueService.insert(jieyue);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JieyueEntity jieyue, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jieyue:{}",this.getClass().getName(),jieyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            jieyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<JieyueEntity> queryWrapper = new EntityWrapper<JieyueEntity>()
            .notIn("id",jieyue.getId())
            .andNew()
            .eq("yonghu_id", jieyue.getYonghuId())
            .eq("tushu_id", jieyue.getTushuId())
            .eq("jieyue_types", jieyue.getJieyueTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JieyueEntity jieyueEntity = jieyueService.selectOne(queryWrapper);
        if(jieyueEntity==null){
            jieyueService.updateById(jieyue);//根据id更新
            if(jieyue.getJieyueTypes() == 2){
                TushuEntity tushuEntity = tushuService.selectById(jieyue.getTushuId());
                YonghuEntity yonghuEntity = yonghuService.selectById(jieyue.getYonghuId());
                yonghuEntity.setJieyueshuliang(yonghuEntity.getJieyueshuliang()+1);
                yonghuService.updateById(yonghuEntity);
                tushuEntity.setTushuKucunNumber(tushuEntity.getTushuKucunNumber()+1);
                tushuService.updateById(tushuEntity);
            }
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        jieyueService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<JieyueEntity> jieyueList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            JieyueEntity jieyueEntity = new JieyueEntity();
//                            jieyueEntity.setYonghuId(Integer.valueOf(data.get(0)));   //借阅用户 要改的
//                            jieyueEntity.setTushuId(Integer.valueOf(data.get(0)));   //借阅书 要改的
//                            jieyueEntity.setJieyueTime(new Date(data.get(0)));          //借阅时间 要改的
//                            jieyueEntity.setHuanshuTime(new Date(data.get(0)));          //最晚还书时间 要改的
//                            jieyueEntity.setJieyueTypes(Integer.valueOf(data.get(0)));   //借阅状态 要改的
//                            jieyueEntity.setCreateTime(date);//时间
                            jieyueList.add(jieyueEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        jieyueService.insertBatch(jieyueList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}


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
 * 出入库
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/tushuChuruInout")
public class TushuChuruInoutController {
    private static final Logger logger = LoggerFactory.getLogger(TushuChuruInoutController.class);

    @Autowired
    private TushuChuruInoutService tushuChuruInoutService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    // 列表详情的表级联service
    @Autowired
    private TushuChuruInoutListService tushuChuruInoutListService;
//    @Autowired
//    private YonghuService yonghuService;
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
        PageUtils page = tushuChuruInoutService.queryPage(params);

        //字典表数据转换
        List<TushuChuruInoutView> list =(List<TushuChuruInoutView>)page.getList();
        for(TushuChuruInoutView c:list){
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
        TushuChuruInoutEntity tushuChuruInout = tushuChuruInoutService.selectById(id);
        if(tushuChuruInout !=null){
            //entity转view
            TushuChuruInoutView view = new TushuChuruInoutView();
            BeanUtils.copyProperties( tushuChuruInout , view );//把实体数据重构到view中

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
    public R save(@RequestBody TushuChuruInoutEntity tushuChuruInout, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tushuChuruInout:{}",this.getClass().getName(),tushuChuruInout.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<TushuChuruInoutEntity> queryWrapper = new EntityWrapper<TushuChuruInoutEntity>()
            .eq("tushu_churu_inout_uuid_number", tushuChuruInout.getTushuChuruInoutUuidNumber())
            .eq("tushu_churu_inout_name", tushuChuruInout.getTushuChuruInoutName())
            .eq("tushu_churu_inout_types", tushuChuruInout.getTushuChuruInoutTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TushuChuruInoutEntity tushuChuruInoutEntity = tushuChuruInoutService.selectOne(queryWrapper);
        if(tushuChuruInoutEntity==null){
            tushuChuruInout.setInsertTime(new Date());
            tushuChuruInout.setCreateTime(new Date());
            tushuChuruInoutService.insert(tushuChuruInout);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TushuChuruInoutEntity tushuChuruInout, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,tushuChuruInout:{}",this.getClass().getName(),tushuChuruInout.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<TushuChuruInoutEntity> queryWrapper = new EntityWrapper<TushuChuruInoutEntity>()
            .notIn("id",tushuChuruInout.getId())
            .andNew()
            .eq("tushu_churu_inout_uuid_number", tushuChuruInout.getTushuChuruInoutUuidNumber())
            .eq("tushu_churu_inout_name", tushuChuruInout.getTushuChuruInoutName())
            .eq("tushu_churu_inout_types", tushuChuruInout.getTushuChuruInoutTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TushuChuruInoutEntity tushuChuruInoutEntity = tushuChuruInoutService.selectOne(queryWrapper);
        if(tushuChuruInoutEntity==null){
            tushuChuruInoutService.updateById(tushuChuruInout);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }
    /**
    * 出库
    */
    @RequestMapping("/outTushuChuruInoutList")
    public R outTushuChuruInoutList(@RequestBody  Map<String, Object> params,HttpServletRequest request){
        logger.debug("outTushuChuruInoutList方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        String role = String.valueOf(request.getSession().getAttribute("role"));

        //取出入库名称并判断是否存在
        String tushuChuruInoutName = String.valueOf(params.get("tushuChuruInoutName"));
        Wrapper<TushuChuruInoutEntity> queryWrapper = new EntityWrapper<TushuChuruInoutEntity>()
            .eq("tushu_churu_inout_name", tushuChuruInoutName)
            ;
        TushuChuruInoutEntity tushuChuruInoutSelectOne = tushuChuruInoutService.selectOne(queryWrapper);
        if(tushuChuruInoutSelectOne != null)
            return R.error(511,"出入库名称已被使用");


    //取当前表的级联表并判断是否前台传入

        Map<String, Integer> map = (Map<String, Integer>) params.get("map");
        if(map == null || map.size() == 0)
            return R.error(511,"列表内容不能为空");


        Set<String> ids = map.keySet();

        List<TushuEntity> tushuList = tushuService.selectBatchIds(ids);
        if(tushuList == null || tushuList.size() == 0){
            return R.error(511,"查数据库查不到数据");
        }else{
            for(TushuEntity w:tushuList){
                Integer value = w.getTushuKucunNumber()-map.get(String.valueOf(w.getId()));
                if(value <0){
                    return R.error(511,"出库数量大于库存数量");
                }
                w.setTushuKucunNumber(value);
            }
        }

        //当前表
        TushuChuruInoutEntity tushuChuruInoutEntity = new TushuChuruInoutEntity<>();
            tushuChuruInoutEntity.setTushuChuruInoutUuidNumber(String.valueOf(new Date().getTime()));
            tushuChuruInoutEntity.setTushuChuruInoutName(tushuChuruInoutName);
            tushuChuruInoutEntity.setTushuChuruInoutTypes(1);
            tushuChuruInoutEntity.setTushuChuruInoutContent("");
            tushuChuruInoutEntity.setInsertTime(new Date());
            tushuChuruInoutEntity.setCreateTime(new Date());

        boolean insertTushuChuruInout = tushuChuruInoutService.insert(tushuChuruInoutEntity);
        if(insertTushuChuruInout){
            //级联表
            ArrayList<TushuChuruInoutListEntity> tushuChuruInoutLists = new ArrayList<>();
            for(String id:ids){
                TushuChuruInoutListEntity tushuChuruInoutListEntity = new TushuChuruInoutListEntity();
                    tushuChuruInoutListEntity.setTushuChuruInoutId(tushuChuruInoutEntity.getId());
                    tushuChuruInoutListEntity.setTushuId(Integer.valueOf(id));
                    tushuChuruInoutListEntity.setTushuChuruInoutListNumber(map.get(id));
                    tushuChuruInoutListEntity.setInsertTime(new Date());
                    tushuChuruInoutListEntity.setCreateTime(new Date());
                tushuChuruInoutLists.add(tushuChuruInoutListEntity);
                tushuService.updateBatchById(tushuList);
            }
            tushuChuruInoutListService.insertBatch(tushuChuruInoutLists);
        }
        return R.ok();
    }

    /**
    *入库
    */
    @RequestMapping("/inTushuChuruInoutList")
    public R inTushuChuruInoutList(@RequestBody  Map<String, Object> params,HttpServletRequest request){
        logger.debug("inTushuChuruInoutList方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        //params:{"map":{"1":2,"2":3},"wuziOutinName":"订单1"}

        String role = String.valueOf(request.getSession().getAttribute("role"));

        //取当前表名称并判断
        String tushuChuruInoutName = String.valueOf(params.get("tushuChuruInoutName"));
        Wrapper<TushuChuruInoutEntity> queryWrapper = new EntityWrapper<TushuChuruInoutEntity>()
            .eq("tushu_churu_inout_name", tushuChuruInoutName)
            ;
        TushuChuruInoutEntity tushuChuruInoutSelectOne = tushuChuruInoutService.selectOne(queryWrapper);
        if(tushuChuruInoutSelectOne != null)
            return R.error(511,"出入库名称已被使用");


        //取当前表的级联表并判断是否前台传入

        Map<String, Integer> map = (Map<String, Integer>) params.get("map");
        if(map == null || map.size() == 0)
            return R.error(511,"列表内容不能为空");

        Set<String> ids = map.keySet();

        List<TushuEntity> tushuList = tushuService.selectBatchIds(ids);
        if(tushuList == null || tushuList.size() == 0){
            return R.error(511,"查数据库查不到数据");
        }else{
            for(TushuEntity w:tushuList){
                w.setTushuKucunNumber(w.getTushuKucunNumber()+map.get(String.valueOf(w.getId())));
            }
        }

        //当前表
        TushuChuruInoutEntity tushuChuruInoutEntity = new TushuChuruInoutEntity<>();
            tushuChuruInoutEntity.setTushuChuruInoutUuidNumber(String.valueOf(new Date().getTime()));
            tushuChuruInoutEntity.setTushuChuruInoutName(tushuChuruInoutName);
            tushuChuruInoutEntity.setTushuChuruInoutTypes(2);
            tushuChuruInoutEntity.setTushuChuruInoutContent("");
            tushuChuruInoutEntity.setInsertTime(new Date());
            tushuChuruInoutEntity.setCreateTime(new Date());


        boolean insertTushuChuruInout = tushuChuruInoutService.insert(tushuChuruInoutEntity);
        if(insertTushuChuruInout){
            //级联表
            ArrayList<TushuChuruInoutListEntity> tushuChuruInoutLists = new ArrayList<>();
            for(String id:ids){
                TushuChuruInoutListEntity tushuChuruInoutListEntity = new TushuChuruInoutListEntity();
                tushuChuruInoutListEntity.setTushuChuruInoutId(tushuChuruInoutEntity.getId());
                tushuChuruInoutListEntity.setTushuId(Integer.valueOf(id));
                tushuChuruInoutListEntity.setTushuChuruInoutListNumber(map.get(id));
                tushuChuruInoutListEntity.setInsertTime(new Date());
                tushuChuruInoutListEntity.setCreateTime(new Date());
                tushuChuruInoutLists.add(tushuChuruInoutListEntity);
                tushuService.updateBatchById(tushuList);
            }
            tushuChuruInoutListService.insertBatch(tushuChuruInoutLists);
        }

        return R.ok();
    }
    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        tushuChuruInoutService.deleteBatchIds(Arrays.asList(ids));
        tushuChuruInoutListService.delete(new EntityWrapper<TushuChuruInoutListEntity>().in("tushu_churu_inout_id",ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<TushuChuruInoutEntity> tushuChuruInoutList = new ArrayList<>();//上传的东西
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
                            TushuChuruInoutEntity tushuChuruInoutEntity = new TushuChuruInoutEntity();
//                            tushuChuruInoutEntity.setTushuChuruInoutUuidNumber(data.get(0));                    //出入库流水号 要改的
//                            tushuChuruInoutEntity.setTushuChuruInoutName(data.get(0));                    //出入库名称 要改的
//                            tushuChuruInoutEntity.setTushuChuruInoutTypes(Integer.valueOf(data.get(0)));   //出入库类型 要改的
//                            tushuChuruInoutEntity.setTushuChuruInoutContent("");//照片
//                            tushuChuruInoutEntity.setInsertTime(date);//时间
//                            tushuChuruInoutEntity.setCreateTime(date);//时间
                            tushuChuruInoutList.add(tushuChuruInoutEntity);


                            //把要查询是否重复的字段放入map中
                                //出入库流水号
                                if(seachFields.containsKey("tushuChuruInoutUuidNumber")){
                                    List<String> tushuChuruInoutUuidNumber = seachFields.get("tushuChuruInoutUuidNumber");
                                    tushuChuruInoutUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> tushuChuruInoutUuidNumber = new ArrayList<>();
                                    tushuChuruInoutUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("tushuChuruInoutUuidNumber",tushuChuruInoutUuidNumber);
                                }
                        }

                        //查询是否重复
                         //出入库流水号
                        List<TushuChuruInoutEntity> tushuChuruInoutEntities_tushuChuruInoutUuidNumber = tushuChuruInoutService.selectList(new EntityWrapper<TushuChuruInoutEntity>().in("tushu_churu_inout_uuid_number", seachFields.get("tushuChuruInoutUuidNumber")));
                        if(tushuChuruInoutEntities_tushuChuruInoutUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TushuChuruInoutEntity s:tushuChuruInoutEntities_tushuChuruInoutUuidNumber){
                                repeatFields.add(s.getTushuChuruInoutUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [出入库流水号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        tushuChuruInoutService.insertBatch(tushuChuruInoutList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}

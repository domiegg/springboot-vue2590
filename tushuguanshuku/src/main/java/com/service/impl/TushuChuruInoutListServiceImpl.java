package com.service.impl;

import com.utils.StringUtil;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.dao.TushuChuruInoutListDao;
import com.entity.TushuChuruInoutListEntity;
import com.service.TushuChuruInoutListService;
import com.entity.view.TushuChuruInoutListView;

/**
 * 出入库详情 服务实现类
 */
@Service("tushuChuruInoutListService")
@Transactional
public class TushuChuruInoutListServiceImpl extends ServiceImpl<TushuChuruInoutListDao, TushuChuruInoutListEntity> implements TushuChuruInoutListService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<TushuChuruInoutListView> page =new Query<TushuChuruInoutListView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}

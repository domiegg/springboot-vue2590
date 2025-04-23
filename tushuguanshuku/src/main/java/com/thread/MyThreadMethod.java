package com.thread;

import com.entity.JieyueEntity;
import com.service.JieyueService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 线程执行方法（做一些项目启动后 一直要执行的操作，比如根据时间自动更改订单状态，比如订单签收30天自动收货功能，比如根据时间来更改状态）
 */
public class MyThreadMethod extends Thread  {

    private JieyueService jieyueService;
    public JieyueService getJieyueService() {
        return jieyueService;
    }
    public void setJieyueService(JieyueService jieyueService) {
        this.jieyueService = jieyueService;
    }

    public void run() {
        while (!this.isInterrupted()) {// 线程未中断执行循环
            try {

                List<JieyueEntity> jieyueEntities = jieyueService.selectList(null);
                ArrayList<JieyueEntity> jieyueList = new ArrayList<>();
                if(jieyueEntities.size()>0){
                    for (JieyueEntity jieyue:jieyueEntities) {
                        JieyueEntity jieyueEntity = new JieyueEntity();
                        if(jieyue.getHuanshuTime().getTime() <= new Date().getTime() && jieyue.getJieyueTypes() !=2){
                            jieyueEntity.setId(jieyue.getId());
                            jieyueEntity.setJieyueTypes(3);
                            jieyueList.add(jieyueEntity);
                        }
                    }
                    if(jieyueList.size()>0){
                        jieyueService.updateBatchById(jieyueList);
                    }
                }

                Thread.sleep(5000); //每隔2000ms执行一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            List<JieyueEntity> jieyueEntities = jieyueService.selectList(null);
//            ArrayList<JieyueEntity> jieyueList = new ArrayList<>();
//            if(jieyueEntities.size()>0){
//                for (JieyueEntity jieyue:jieyueEntities) {
//                    JieyueEntity jieyueEntity = new JieyueEntity();
//                    if(jieyue.getHuanshuTime().getTime() <= new Date().getTime() && jieyueEntity.getJieyueTypes() !=2){
//                        jieyueEntity.setId(jieyue.getId());
//                        jieyueEntity.setJieyueTypes(3);
//                    }
//                    jieyueList.add(jieyueEntity);
//                }
//                jieyueService.updateBatchById(jieyueList);
//            }


            System.out.println("线程执行中:" + System.currentTimeMillis());
        }
    }
}

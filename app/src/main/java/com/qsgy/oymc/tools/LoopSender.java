package com.qsgy.oymc.tools;

import com.qsgy.oymc.BaseClass.SendProvider;
import com.qsgy.oymc.ControlActivity;

/**
 * Created by 欧阳浩 on 2017/11/30.
 * this is a tool help to send messages again and again
 * 用一个sendprovider来构造这个类，启动线程开始刷信息
 */

public class LoopSender implements Runnable {
    SendProvider provider;
    String sendS="";
    long loopTime=150;//循环跑的的间断时间
    public LoopSender(SendProvider provider){
        this .provider=provider;
    }
   // int exCount=3;
    @Override
    public void run() {
        while (true) {
            if (provider.isReady()||sendS!=provider.SendM()) {


               sendS=provider.SendM();
                    if (sendS!="111") {
                        ((ControlActivity) ControlActivity.context).SendLight(sendS);
                    }
                    try {
                        Thread.currentThread().sleep(loopTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                   /* if (exCount==0){
                        exCount=3;
                    }
                    exCount--;*/
                provider.over();//已经发送了信息

            }
            try {
                Thread.currentThread().sleep(loopTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

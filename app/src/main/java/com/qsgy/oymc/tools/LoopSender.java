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
    long loopTime=100;//循环跑的的间断时间
    public LoopSender(SendProvider provider){
        this .provider=provider;
    }
    @Override
    public void run() {
        while (true) {
            if (provider.isReady()) {
                String[] sendS=new String[provider.SendM().length];
               sendS=provider.SendM();
                for(int i=0;i<sendS.length;i++)
                {
                    ((ControlActivity) ControlActivity.context).SendLight(sendS[i]);
                    try {
                        Thread.currentThread().sleep(loopTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

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

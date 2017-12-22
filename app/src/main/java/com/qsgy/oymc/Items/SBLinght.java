package com.qsgy.oymc.Items;

/**
 * Created by 欧阳浩 on 2017/11/15.
 */
import android.widget.*;
import android.view.*;


import com.qsgy.oymc.BaseClass.SendProvider;
import com.qsgy.oymc.R;
import com.qsgy.oymc.tools.LoopSender;
import com.qsgy.oymc.tools.StringHelper;

import java.util.Timer;
import java.util.TimerTask;

public class SBLinght implements SendProvider
{
    public TextView valueText;
    public TextView nameText;
    public SeekBar seekBar;
    public Switch wAuto;//自动灯开关
    public int LightLV;//灯光强度
    public Boolean isAuto;//是否自动亮度
    String type;//
    boolean isReady=false;//is ready to send message?
    private String sendM="111";//send what
    public SBLinght(View convertView) {
        valueText = (TextView) convertView.findViewById(R.id.light_lv);
        seekBar = (SeekBar) convertView.findViewById(R.id.lv_bar);
        nameText = (TextView) convertView.findViewById(R.id.light_show);
        seekBar.setMax(255);
    }
    public void setWork(final String type){
        this.type=type;
        nameText.setText(type+"亮度");
        valueText.setText(0+"lv");
        seekBar.setProgress(0);
        Thread sendThread=new Thread(new LoopSender(this));
        sendThread.start();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueText.setText(seekBar.getProgress()+"lv");
                sendM=type+StringHelper.Send_Format_W255(""+seekBar.getProgress());
                synchronized (this){
                    isReady=true;
                }
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isReady=true;
                    }
                },50);
           // ((ControlActivity)ControlActivity.context).SendLight(type+mess);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                synchronized (this){
                    isReady=true;
                }
            }
        });
    }

    @Override
    public boolean isReady() {
        synchronized(this) {
            return isReady;
        }
    }

    @Override
    public void over() {

            isReady=false;

    }

    @Override
    public String SendM() {
        return sendM;
    }
}




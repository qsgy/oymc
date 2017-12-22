package com.qsgy.oymc.Items;

import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.SeekBar;

import com.qsgy.oymc.tools.LoopSender;
import com.qsgy.oymc.tools.StringHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 欧阳浩 on 2017/12/19.
 */

public class ColorTemper extends SBLinght
{
    private String sendMM="";
    @RequiresApi(api = 26)
    public ColorTemper(View convertView) {
        super(convertView);
        seekBar.setMax(100);

    }

    @Override
    public void setWork(String s) {
        nameText.setText("色温");
        valueText.setText(0+"lv");
        seekBar.setProgress(0);
        Thread sendThread=new Thread(new LoopSender(this));
        sendThread.start();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueText.setText((seekBar.getProgress()*40+3000)+"K");
                sendMM="C"+ StringHelper.Send_Format_W255(""+seekBar.getProgress());

        synchronized (this){
            isReady=true;
        }
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isReady=true;
                    }
                },50);
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
    public String SendM() {
        return sendMM;
    }
}

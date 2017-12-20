package com.qsgy.oymc.Items;

import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.SeekBar;

import com.qsgy.oymc.tools.LoopSender;
import com.qsgy.oymc.tools.StringHelper;

/**
 * Created by 欧阳浩 on 2017/12/19.
 */

public class ColorTemper extends SBLinght
{
    private String[] sendM=new String[ ]{""};
    @RequiresApi(api = 26)
    public ColorTemper(View convertView) {
        super(convertView);
        seekBar.setMax(4000);

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
                valueText.setText((seekBar.getProgress()+3000)+"K");
                sendM[0]="C"+ StringHelper.Send_Format_W255(""+seekBar.getProgress()/4000);

        synchronized (this){
            isReady=true;
        }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public String[] SendM() {
        return sendM;
    }
}

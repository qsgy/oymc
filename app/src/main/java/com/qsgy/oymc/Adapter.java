package com.qsgy.oymc;

import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.qsgy.oymc.Items.ColorTemper;
import com.qsgy.oymc.Items.LightKey;
import com.qsgy.oymc.Items.SBLinght;

/**
 * Created by 欧阳浩 on 2017/11/22.
 */

public  class Adapter extends BaseAdapter{

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = 26)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SBLinght sblight;
        final LightKey lightK;
        convertView=null;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(parent
                    .getContext());

          /*  convertView = layoutInflator.inflate(R.layout.light_lv,
                    null);*/
            switch (position)
            {
                case 0:
                    convertView = layoutInflator.inflate(R.layout.light_lv,
                            null);
                    sblight = new SBLinght(convertView);
                    sblight.setWork("W");
                    convertView.setTag(sblight);
                    break;
                case 1:
                    convertView = layoutInflator.inflate(R.layout.light_lv,
                            null);
                    sblight = new SBLinght(convertView);
                    sblight.setWork("Y");
                    convertView.setTag(sblight);
                    break;
                case 2:
                  /*  convertView = layoutInflator.inflate(R.layout.receive,
                            null);*/
                    convertView = layoutInflator.inflate(R.layout.light_lv,
                            null);
                    sblight = new ColorTemper(convertView);
                    sblight.setWork("K");
                    convertView.setTag(sblight);
                    break;
                case 3:
                    convertView = layoutInflator.inflate(R.layout.btn,
                            null);
                    lightK = new LightKey(convertView);
                    lightK.setWork();
                    convertView.setTag(lightK);
                    break;
                case 4:
                    convertView = layoutInflator.inflate(R.layout.image,
                            null);
                    break;
                default:

                    break;
            }

        } else {

        }


        return convertView;
    }

}




package com.qsgy.oymc.Items;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.qsgy.oymc.ControlActivity;
import com.qsgy.oymc.R;

/**
 * Created by 欧阳浩 on 2017/12/20.
 */

public class LightKey {
    Button openL;
    Button closeL;
    String oKey="K111";
    String cKey="K000";
    public LightKey(View cw){
        openL=(Button)cw.findViewById(R.id.openL);
        closeL=(Button)cw.findViewById(R.id.closeL);;
    }

    public void setWork(){
        openL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ((ControlActivity)ControlActivity.context).SendLight(oKey);
                }catch (Exception e){
                    Toast.makeText(((ControlActivity)ControlActivity.context),"Erorr"+e.toString(),Toast.LENGTH_SHORT ).show();
                }

            }
        });

        closeL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ControlActivity)ControlActivity.context).SendLight(cKey);
            }
        });
    }
}

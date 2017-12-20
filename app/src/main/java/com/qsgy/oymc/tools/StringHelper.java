package com.qsgy.oymc.tools;

/**
 * Created by 欧阳浩 on 2017/11/30.
 */

public class StringHelper {
    //在前面补0到三位
    public static String Send_Format_W255(String s) {
        if (s.length()==1)s="00"+s;
        else if (s.length()==2)s="0"+s;
        else if (s.length()==0||s.length()>3)s="000";
        return  s;
    }
}

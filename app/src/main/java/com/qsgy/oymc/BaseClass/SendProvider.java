package com.qsgy.oymc.BaseClass;

/**
 * Created by 欧阳浩 on 2017/11/30.
 * 需要循环发送信息的类可以实现此接口，isReady为true时利用ControlActivity的sendlight方法发送尾部增加了\r\n的信息，然后重置为false，SendM为发送的字符串，编码默认utf-8
 */

public interface SendProvider {
    boolean isReady();
    void over();
    String SendM();

}

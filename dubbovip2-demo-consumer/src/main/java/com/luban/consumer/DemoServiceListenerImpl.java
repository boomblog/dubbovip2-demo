package com.luban.consumer;

import com.luban.DemoServiceListener;

public class DemoServiceListenerImpl implements DemoServiceListener {

    @Override
    public void changed(String msg) {
        System.out.println("被回调了："+msg);
    }
}

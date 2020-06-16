package com.grpc.model.EventBus.Observer;

import java.util.Observable;
import java.util.Observer;

public class MyObserver implements Observer {
    /**
     * 观察者（学生）name
     */
    private String name;
    public MyObserver(Observable o, String name) {
        o.addObserver(this);
        this.name = name;
    }
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("观察者（学生）" + name + "收到作业！《" + arg + "》"+"目标的观察者数量=" + o.countObservers());
    }
}


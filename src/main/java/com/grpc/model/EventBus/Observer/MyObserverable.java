package com.grpc.model.EventBus.Observer;

import java.util.Observable;

public class MyObserverable extends Observable {
    //被观察者数据
    private String data="Java从入门到放弃";
    public String getData() {
        return data;
    }
    /**
     * 如果有如果改变
     * @param data
     */
    public void setData(String data) {
        if (!this.data.equals(data)) {
            this.data = data;
            //更改变化状态
            setChanged();
        }
        //通知注册的观察者
        notifyObservers(data);
    }
}


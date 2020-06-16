package com.grpc.model.EventBus.Observer;

public class ObserverTest {

    public static void main(String[] args) {
        //1.构造被观察目标。（假如现实场景中的老师）
        MyObserverable observerable = new MyObserverable();
        //2.构造2个观察者实现类:添加观察者进观察目标  （现实场景中的学生，每来一个新学生，要加入老师的观察者名录中）
        MyObserver observer1 = new MyObserver(observerable, "tom");
        MyObserver observer2 = new MyObserver(observerable, "jerry");
        MyObserver observer3 = new MyObserver(observerable, "Pom");
        //3.被观察者（老师）发布今天的作业任务。其注册的观察者们（学生们）响应。
        observerable.setData("Java从入门到放弃");
    }

}

package com.grpc.model.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private int i=0;
    private AtomicInteger atomicI=new AtomicInteger(0);

    private  void safeCount(){
        for(;;){
            int i=atomicI.get();
            //compareAndSet=cas,以原子方式将同步状态设置为给定的更新值
            boolean suc=atomicI.compareAndSet(i,++i);
            if(suc){
                break;
            }
        }
    }

    private void Count(){
        i++;
    }

    public static void main(String[] args) {
        final Counter cas=new Counter();
        List<Thread> ts=new ArrayList<Thread>(600);
        long start=System.currentTimeMillis();
        for(int j=0;j<100;j++){
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<1000;i++){
                        cas.Count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        for(Thread t:ts){
            t.start();
        }
        for(Thread t:ts){
            try{
                t.join();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis()-start);
    }
}

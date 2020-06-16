package com.grpc.model.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        lock.lock();
        Thread thread=new Thread(new SignalThread());
        thread.setName("子线程");
        thread.start();
        System.out.println(Thread.currentThread().getName()+"等待通知");
        try {
            condition.await();
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName()+"恢复运行");
    }
    static class SignalThread implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName()+"通知");
            } finally {
                lock.unlock();
            }
        }
    }
}

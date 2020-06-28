package com.grpc.model.FutureTaskAndCallable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureTask;

import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TutureTest {

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    public void Test() throws ExecutionException, InterruptedException, TimeoutException {
        Long timeStart = System.currentTimeMillis();
        FutureTask<String> task1=new FutureTask<>(()->{
            Thread.sleep(5000);
            return "已执行1";
        });
        FutureTask<String> task2=new FutureTask<>(()->{
            Thread.sleep(10000);
            return "已执行2";
        });
        threadPoolTaskExecutor.execute(task1);
        threadPoolTaskExecutor.execute(task2);

        System.out.println("可结果："+task1.get()+task2.get(1, TimeUnit.SECONDS));
        Long timeEnd = System.currentTimeMillis();
        System.out.println("执行完毕，"+(timeEnd-timeStart));



        ListenableFutureTask<String> task = new ListenableFutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000); // 模拟耗时操作
                return "success";
            }
        });
        task.addCallback(new ListenableFutureCallback<String>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("调用失败");
            }

            @Override
            public void onSuccess(String s) {
                System.out.println("调用成功：" + s);
            }
        });
        Executors.newSingleThreadExecutor().submit(task);
    }

    @Test
    public void Test2() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "say helloWorld!!!";
            }
        });
        System.out.println(future.get());// 通过get返回结果
    }
}

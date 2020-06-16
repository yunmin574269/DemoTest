package com.grpc.model;

import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureTest {

    @Test
    public void Test() throws ExecutionException, InterruptedException {
        // 在 Java8 中，推荐使用 Lambda 来替代匿名 Supplier 实现类
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }

            return "I have completed";
        });

        System.out.println("开始了1");
        CompletableFuture<String> upperfuture = future.thenApply(String::toUpperCase);
        System.out.println(upperfuture.get());
        System.out.println("开始了2");

    }

    @Test
    public void Test2() throws ExecutionException, InterruptedException {
        // 在 Java8 中，推荐使用 Lambda 来替代匿名 Supplier 实现类
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }

            return "I have completed";
        });
        System.out.println("开始了1");
        System.out.println(future.get());
    }

    @Test
    public void Test3() throws ExecutionException, InterruptedException {
        // 在 Java8 中，推荐使用 Lambda 来替代匿名 Supplier 实现类
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }

            return "I have completed";
        });

        future.thenAccept(s -> {
            System.out.println(s);
        });

        // Waits if necessary for this future to complete, and then returns its result.
        future.get();
    }

    @Test
    public void Test4() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

            return "Hello ";
        }).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }

            return s + "World";
        }));

        System.out.println(future.get()); // Hello World

        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

            return "Hello ";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }

            return "World";
        }), (s1, s2) -> s1 + s2);

        System.out.println(future2.get());
    }

    @Test
    public void Test5() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "World");


        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2);

        // 这个方法不会合并结果，可以看到他的返回值是 Void 类型
        combinedFuture.get();

        // 我们需要手动来处理每一个并行异步任务的结果
        String combined = Stream.of(future1, future2)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        System.out.println(combined); // Hello World
    }

    @Test
    public void Test6() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }

                    return "World";
                }
        );


        CompletableFuture<Object> combinedFuture
                = CompletableFuture.anyOf(future1, future2);

        System.out.println(combinedFuture.get()); // Hello
    }

    @Test
    public void Test7() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {

            @Override
            public Integer get() {
                int i= 10/0;
                return new Random().nextInt(10);
            }
        }).handle(new BiFunction<Integer, Throwable, Integer>() {
            @Override
            public Integer apply(Integer param, Throwable throwable) {
                int result = -1;
                if(throwable==null){
                    result = param * 2;
                }else{
                    System.out.println(throwable.getMessage());
                }
                return result;
            }
        });
        System.out.println(future.get());
    }

    @Test
    public void Test8(){
        List<CompletableFuture<Void>> list=new ArrayList<>();
        int y=10;
        for(int x=1;x<10;x++){
            long sleeps=1000;
            int finalY = y;
            CompletableFuture future = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(sleeps);
                } catch (Exception e) {
                }
                return "Hello"+sleeps+ finalY;
            }).thenAccept((r)->{
                System.out.println(r);
            });
            list.add(future);
            y--;
        }
        CompletableFuture.allOf(list.toArray(new CompletableFuture[]{})).join();
        System.out.println("执行完毕等待");
    }

    @Test
    public void test9(){
        for(int x=0;x<10;x++){
            tb(x);
        }
    }

    public void tb(int x){
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        System.out.println("----------------->>"+x);
    }

    @Test
    public void test10() throws ExecutionException, InterruptedException {
        int y=10;
        for(int x=1;x<10;x++){
            int finalY = y;
            CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(finalY);
                } catch (InterruptedException e) {
                }
                System.out.println("run end ...");
                return System.currentTimeMillis();
            });
//            System.out.println("time = "+0);
            long time = future.get();
            System.out.println("time "+x+"= "+time);
            y--;
        }

    }

    @Test
    public void test11() throws InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if(new Random().nextInt()%2>=0) {
                int i = 12/0;
            }
            System.out.println("run end ...");
        });

        future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void t, Throwable action) {
                System.out.println("执行完成！");
            }

        });
        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable t) {
                System.out.println("执行失败！"+t.getMessage());
                return null;
            }
        });

        TimeUnit.SECONDS.sleep(2);
    }
}

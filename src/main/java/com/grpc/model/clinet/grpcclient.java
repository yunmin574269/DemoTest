package com.grpc.model.clinet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class grpcclient{
//    private final ManagedChannel channel;
//    private final GreeterGrpc.GreeterBlockingStub blockingStub;
//
//    public grpcclient(String host,int port){
//        channel = ManagedChannelBuilder.forAddress(host,port)
//                .usePlaintext(true)
//                .build();
//
//        blockingStub = GreeterGrpc.newBlockingStub(channel);
//    }
//
//
//    public void shutdown() throws InterruptedException {
//        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//    }
//
//    public  void greet(String name){
//        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
//        HelloReply response;
//        try{
//            response = blockingStub.sayHello(request);
//        } catch (StatusRuntimeException e)
//        {
//            return;
//        }
//    }

//    public static void main(String[] args) throws InterruptedException {
//        Optional<String> opt = Optional.of("zhuoli");
//        System.out.println(opt.isPresent());
//
//        opt = Optional.ofNullable(null);
//        System.out.println(opt.isPresent());
//
//        String s=null;
//        String ss = Optional.ofNullable(s).orElse("11111");
//        opt.ifPresent(name -> System.out.println(name.length()));
//
//        LocalDateTime dateTime = LocalDateTime.now();
//        dateTime=dateTime.minusDays(1).minusHours(1).minusMonths(1);
//
//        DateTimeFormatter f4 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        System.out.println(dateTime.format(f4));
//
//
//
//        String[] strArr = {"迪丽热巴,女","郑爽,女","杨紫,女"};
//        printInfo(strArr,(message)->{
//            System.out.print("姓名:" + message.split(",")[0] + "。  ");
//        },(message)->{
//            System.out.println("性别:" + message.split(",")[1] + "。");
//
//        });
//
//        // 获取任务的开始时间
//        long millis = System.currentTimeMillis();
//
//        long sum = LongStream.rangeClosed(0L, 10000000000L)
//                .parallel()
//                .sum();
//        // 获取任务的结束时间
//        long millis2 = System.currentTimeMillis();
//        System.out.println(sum);
//        // 84844
//        System.out.println("使用Java8并行流方式计算共用时："+(millis2-millis));
//
//        Optional<String> ofNullable = Optional.ofNullable(new String());
//        Optional<String> ofNullable2 = Optional.ofNullable(null);
//        System.out.println("\n"+ofNullable);
//        System.out.println(ofNullable2);
//        System.out.println();
//
//
//        // orElse(T t)
//        String orElse = ofNullable2.orElse(new String("jack"));
//        System.out.println(orElse);
//
//        // orElseGet(Supplier s)
//        String orElseGet = ofNullable.orElseGet(() -> new String("1111"));
//        System.out.println(orElseGet);
//
//    }

    public static void printInfo(String[] strArr, Consumer<String> con1, Consumer<String> con2){

        for (int i = 0; i < strArr.length; i++) {
            con1.andThen(con2).accept(strArr[i]);
        }

    }

    public static void main(String[] args)  {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date1="2019-09-18 10:14:30";
//        String date2="2019-09-18 17:13:30";
//        long daysBetween = (df.parse(date2).getTime() - df.parse(date1).getTime()) / (60 * 60 * 1000);
        System.out.println(df.format(new Date(System.currentTimeMillis()-24*60*60*1000)));
    }

    public static class Shop {
        public static double getPrice(String product) throws InterruptedException {
            //查询商品的数据库，或链接其他外部服务获取折扣
            Thread.sleep(2000);
            return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
        }
    }

    public static CompletableFuture<Double> getPriceAsync(String product){
        //创建CompletableFuture对象
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();

        new Thread (()->{
            double price = 0;
            try {
                price = Shop.getPrice(product);
                //需要长时间计算的任务结束并得出结果时，设置future的返回值
                futurePrice.complete(price);
            } catch (InterruptedException e) {
                //抛出异常
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    //CompletableFuture
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
////        System.out.println("begin");
////        CompletableFuture<Double> futurePrice = getPriceAsync("ss");
////        System.out.println("doSomething");
////        try {
////            System.out.println(futurePrice.get(1, TimeUnit.SECONDS));
////        } catch (TimeoutException e) {
////            System.out.print(e);
////        }
////        System.out.println("end");
////        CompletableFuture future1 = CompletableFuture.runAsync(() -> System.out.println("runAsync"));
////        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "supplyAsync");
////
////        System.out.println(future1.get());
////        System.out.println(future2.get());
//
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            ThreadUtil.sleepexception(100);
//            return 20;
//        }).whenCompleteAsync((v, e) -> {
//            System.out.println(v);
//            System.out.println(e);
//        }).exceptionally((e) ->{
//            System.out.println(e);
//            return 10;
//        });
//        System.out.println(future.get());
//
//
//        //接受到数据，返回值
//        CompletableFuture<Integer> future2 = CompletableFuture
//                .supplyAsync(() -> 1)
//                .thenApply((a) -> {
//                    ThreadUtil.sleep(1000);
//                    System.out.println(a);//1
//                    return a * 10;
//                }).thenApply((a) -> {
//                    ThreadUtil.sleep(1000);
//                    System.out.println(a);//10
//                    return a + 10;
//                }).thenApply((a) -> {
//                    ThreadUtil.sleep(1000);
//                    System.out.println(a);//20
//                    return a - 5;
//                });
//        System.out.println(future2.get());//15
//
//        //会接受当前一个的的值和上一个的值
//        CompletableFuture
//                .supplyAsync(() -> 1)
//                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2), (a, b) -> {
//                    System.out.println(a);
//                    System.out.println(b);
//                }).get();
//
//        //接受上一个的值，并产生新的值返回
//        CompletableFuture<Integer> future3 = CompletableFuture
//                .supplyAsync(() -> 1)
//                .thenApply((a) -> {
//                    ThreadUtil.sleep(1000);
//                    return a + 10;
//                })
//                .thenCompose((s) -> {
//                    System.out.println(s); //11
//                    return CompletableFuture.supplyAsync(() -> s * 5);
//                });
//
//        System.out.println(future3.get());//55
//
//        //两个方法并行处理,thenCombine 和 supplyAsync 不一定哪个先哪个后，是并行执行的。
//        Random random = new Random();
//        CompletableFuture<Integer> future4 = CompletableFuture
//                .supplyAsync(() -> {
//                    ThreadUtil.sleep(random.nextInt(1000));
//                    System.out.println("supplyAsync");
//                    return 2;
//                }).thenApply((a) -> {
//                    ThreadUtil.sleep(random.nextInt(1000));
//                    System.out.println("thenApply");
//                    return a * 3;
//                }).thenCombine(CompletableFuture.supplyAsync(() -> {
//                    ThreadUtil.sleep(random.nextInt(1000));
//                    System.out.println("thenCombineAsync");
//                    return 10;
//                }), (a, b) -> {
//                    System.out.println("a:"+a);
//                    System.out.println("b:"+b);
//                    return a + b;
//                });
//
//        System.out.println(future4.get());
//
//        //acceptEither 没有返回值，applyToEither 有返回值
//
//        //allOf 把有方法都执行完才往下执行，没有返回值, 有时输出1 2 有时输出 2 1
//        CompletableFuture.allOf(
//                CompletableFuture.runAsync(() -> {
//                    ThreadUtil.sleep(random.nextInt(1000));
//                    System.out.println(1);
//                }),
//                CompletableFuture.runAsync(() -> {
//                    ThreadUtil.sleep(random.nextInt(1000));
//                    System.out.println(2);
//                }))
//                .get();
//
//        //anyOf 任务一个方法执行完都往下执行，返回一个Object类型的值,   输出结果有时为1 有时间为 2
//        Object obj = CompletableFuture.anyOf(
//                CompletableFuture.supplyAsync(() -> {
//                    ThreadUtil.sleep(random.nextInt(1000));
//                    return 1;
//                }),
//                CompletableFuture.supplyAsync(() -> {
//                    ThreadUtil.sleep(random.nextInt(1000));
//                    return 2;
//                })).get();
//
//        System.out.println(obj);
//
//    }

    public static class ThreadUtil {
        public static void sleep(long ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        public static void sleepexception(long ms) {
            throw new RuntimeException("NullException");
        }
    }
}
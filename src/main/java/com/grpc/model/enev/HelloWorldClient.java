package com.grpc.model.enev;

import com.grpc.model.protobuf.helloworld.GreeterGrpc;
import com.grpc.model.protobuf.helloworld.HelloWorldServiceProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class HelloWorldClient {

    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;
    private final GreeterGrpc.GreeterStub blockingStubstream;

    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public HelloWorldClient(String host, int port) {

        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        blockingStub = GreeterGrpc.newBlockingStub(channel);
        blockingStubstream=GreeterGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /** Say hello to server. */
    public void greet(String name) {
        System.out.println("Will try to greet " + name + " ...");
        HelloWorldServiceProto.HelloRequest request = HelloWorldServiceProto.HelloRequest.newBuilder().setName(name).setSex(" 女").build();
        HelloWorldServiceProto.HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            System.out.println("RPC failed: "+e.getStatus());
            return;
        }
        System.out.println("Greeting: " + response.getMessage());
    }

    public void greet2(String name) {
        System.out.println("Will try to greet " + name + " ...");
        HelloWorldServiceProto.HelloRequest request = HelloWorldServiceProto.HelloRequest.newBuilder().setName(name).setSex(" 女").build();
        Iterator<HelloWorldServiceProto.HelloReply> iterator=blockingStub.serverSideStreamFun(request);
        long start = System.currentTimeMillis();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getMessage());
        }
        channel.shutdown();
        System.out.println(System.currentTimeMillis() - start + " MS");
    }

    public void greet3(String name) throws InterruptedException {
        StreamObserver<HelloWorldServiceProto.HelloReply> responseData = new StreamObserver<HelloWorldServiceProto.HelloReply>() {
            @Override
            public void onNext(HelloWorldServiceProto.HelloReply value) {
                // TODO Auto-generated method stub
                System.out.println(value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                // TODO Auto-generated method stub
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                // TODO Auto-generated method stub
                // 关闭channel
                channel.shutdown();
            }
        };
        //stream grpc与之前获取不同
        StreamObserver<HelloWorldServiceProto.HelloRequest> requestData = blockingStubstream.clientSideStreamFun(responseData);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            requestData.onNext(HelloWorldServiceProto.HelloRequest.newBuilder().setName("你好" + i).build());
        }
        requestData.onCompleted();
        System.out.println(System.currentTimeMillis() - start + " MS");
        // 由于是异步获得结果，所以sleep 10秒
        Thread.sleep(10000);
    }

    public void greet4(String name) throws InterruptedException {
        StreamObserver<HelloWorldServiceProto.HelloReply> responseData = new StreamObserver<HelloWorldServiceProto.HelloReply>() {
            @Override
            public void onNext(HelloWorldServiceProto.HelloReply value) {
                // TODO Auto-generated method stub
                System.out.println(value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                // TODO Auto-generated method stub
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                // TODO Auto-generated method stub
                // 关闭channel
                channel.shutdown();
            }
        };
        StreamObserver<HelloWorldServiceProto.HelloRequest> requestData = blockingStubstream.twoWayStreamFun(responseData);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            requestData.onNext(HelloWorldServiceProto.HelloRequest.newBuilder().setName("你好" + i).build());
        }
        requestData.onCompleted();
        System.out.println(System.currentTimeMillis() - start + " MS");
        // 由于是异步获得结果，所以sleep 10秒
        Thread.sleep(10000);
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {
        HelloWorldClient client = new HelloWorldClient("localhost", 50051);
        try {

            String user = "world";
            if (args.length > 0) {
                user = args[0];
            }
//            client.greet(user);
//            client.greet2(user);
//            client.greet3(user);
            client.greet4(user);
        } finally {
            client.shutdown();
        }
    }

}

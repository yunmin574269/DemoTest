package com.grpc.model.enev;

import com.grpc.model.protobuf.helloworld.GreeterGrpc;
import com.grpc.model.protobuf.helloworld.HelloWorldServiceProto;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloWorldServer  {

    /* The port on which the server should run */
    private int port = 50051;
    private Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new GreeterImpl())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                HelloWorldServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }

    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        /** 原子Integer */
        public AtomicInteger count = new AtomicInteger(0);

        @Override
        public void sayHello(HelloWorldServiceProto.HelloRequest req, StreamObserver<HelloWorldServiceProto.HelloReply> responseObserver) {
            System.out.println("call sayHello");
            HelloWorldServiceProto.HelloReply reply = HelloWorldServiceProto.HelloReply.newBuilder().setMessage("Hello " + req.getName() + req.getSex()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            System.out.println(count.incrementAndGet() + Thread.currentThread().getName());
        }

        @Override
        public void serverSideStreamFun(HelloWorldServiceProto.HelloRequest req, StreamObserver<HelloWorldServiceProto.HelloReply> responseObserver){
            System.out.println("call sayHello2");
//            HelloWorldServiceProto.HelloReply reply1 = HelloWorldServiceProto.HelloReply.newBuilder().setMessage("Hello1 " + req.getName() + req.getSex()).build();
//            HelloWorldServiceProto.HelloReply reply2 = HelloWorldServiceProto.HelloReply.newBuilder().setMessage("Hello2 " + req.getName() + req.getSex()).build();
//            responseObserver.onNext(reply1);
//            responseObserver.onNext(reply2);
            for(int x=0;x<5;x++){
                responseObserver.onNext(HelloWorldServiceProto.HelloReply.newBuilder().setMessage("Hello"+x+" "+ req.getName() + req.getSex()).build());
            }
            responseObserver.onCompleted();
            System.out.println(count.incrementAndGet() + Thread.currentThread().getName());
        }

        @Override
        public StreamObserver<HelloWorldServiceProto.HelloRequest> clientSideStreamFun(StreamObserver<HelloWorldServiceProto.HelloReply> responseObserver) {
            // TODO Auto-generated method stub
            return new StreamObserver<HelloWorldServiceProto.HelloRequest>() {
                Map<String,String> map=new HashMap<>();
                private HelloWorldServiceProto.HelloReply.Builder builder = HelloWorldServiceProto.HelloReply.newBuilder();

                @Override
                public void onNext(HelloWorldServiceProto.HelloRequest value) {
                    // TODO Auto-generated method stub
                    System.out.println("请求参数：" + value.getName()+"--->"+value.getSex());
                    map.put(value.getName(),value.getSex());
                }

                @Override
                public void onError(Throwable t) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onCompleted() {
                    // TODO Auto-generated method stub
                    builder.setMessage("数据接收完成,参数数量："+map.size());
                    responseObserver.onNext(builder.build());
                    responseObserver.onCompleted();
                }

            };
        }

        @Override
        public StreamObserver<HelloWorldServiceProto.HelloRequest> twoWayStreamFun(StreamObserver<HelloWorldServiceProto.HelloReply> responseObserver) {
            // TODO Auto-generated method stub
            return new StreamObserver<HelloWorldServiceProto.HelloRequest>() {

                @Override
                public void onNext(HelloWorldServiceProto.HelloRequest value) {
                    // TODO Auto-generated method stub
                    System.out.println("请求参数：" + value.getName()+"--->"+value.getSex());
                    responseObserver.onNext(HelloWorldServiceProto.HelloReply.newBuilder().setMessage(value.getName() + "，欢迎你的加入").build());
                }

                @Override
                public void onError(Throwable t) {
                    // TODO Auto-generated method stub
                    t.printStackTrace();
                }

                @Override
                public void onCompleted() {
                    // TODO Auto-generated method stub
                    responseObserver.onCompleted();
                }

            };
        }

    }

}

package com.grpc.model.server;

import com.grpc.model.protobuf.helloworld.GreeterGrpc;
import com.grpc.model.protobuf.helloworld.HelloWorldServiceProto;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class grpcserver {

    private int port = 50051;
    private Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService((BindableService) new GreeterImpl())
                .build()
                .start();

        Runtime.getRuntime().addShutdownHook(new Thread(){

            @Override
            public void run(){

                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                grpcserver.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop(){
        if (server != null){
            server.shutdown();
        }
    }

    // block 一直到退出程序
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null){
            server.awaitTermination();
        }
    }


    public  static  void main(String[] args) throws IOException, InterruptedException {

        final grpcserver server = new grpcserver();
        server.start();
        server.blockUntilShutdown();
    }


    //实现 定义一个实现服务接口的类
    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(HelloWorldServiceProto.HelloRequest req, StreamObserver<HelloWorldServiceProto.HelloReply> responseObserver){
            HelloWorldServiceProto.HelloReply reply = HelloWorldServiceProto.HelloReply.newBuilder().setMessage(("Hello "+req.getName())).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }


}
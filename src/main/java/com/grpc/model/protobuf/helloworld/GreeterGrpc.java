package com.grpc.model.protobuf.helloworld;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *服务端接口类
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.22.1)",
    comments = "Source: helloworld.proto")
public final class GreeterGrpc {

  private GreeterGrpc() {}

  public static final String SERVICE_NAME = "grpc.Greeter";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest,
      com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest.class,
      responseType = com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest,
      com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> getSayHelloMethod() {
    io.grpc.MethodDescriptor<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest, com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> getSayHelloMethod;
    if ((getSayHelloMethod = GreeterGrpc.getSayHelloMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getSayHelloMethod = GreeterGrpc.getSayHelloMethod) == null) {
          GreeterGrpc.getSayHelloMethod = getSayHelloMethod = 
              io.grpc.MethodDescriptor.<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest, com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpc.Greeter", "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("SayHello"))
                  .build();
          }
        }
     }
     return getSayHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest,
      com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> getServerSideStreamFunMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ServerSideStreamFun",
      requestType = com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest.class,
      responseType = com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest,
      com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> getServerSideStreamFunMethod() {
    io.grpc.MethodDescriptor<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest, com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> getServerSideStreamFunMethod;
    if ((getServerSideStreamFunMethod = GreeterGrpc.getServerSideStreamFunMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getServerSideStreamFunMethod = GreeterGrpc.getServerSideStreamFunMethod) == null) {
          GreeterGrpc.getServerSideStreamFunMethod = getServerSideStreamFunMethod = 
              io.grpc.MethodDescriptor.<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest, com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "grpc.Greeter", "ServerSideStreamFun"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("ServerSideStreamFun"))
                  .build();
          }
        }
     }
     return getServerSideStreamFunMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest,
      com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> getClientSideStreamFunMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClientSideStreamFun",
      requestType = com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest.class,
      responseType = com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest,
      com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> getClientSideStreamFunMethod() {
    io.grpc.MethodDescriptor<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest, com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> getClientSideStreamFunMethod;
    if ((getClientSideStreamFunMethod = GreeterGrpc.getClientSideStreamFunMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getClientSideStreamFunMethod = GreeterGrpc.getClientSideStreamFunMethod) == null) {
          GreeterGrpc.getClientSideStreamFunMethod = getClientSideStreamFunMethod = 
              io.grpc.MethodDescriptor.<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest, com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "grpc.Greeter", "ClientSideStreamFun"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("ClientSideStreamFun"))
                  .build();
          }
        }
     }
     return getClientSideStreamFunMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest,
      com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> getTwoWayStreamFunMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TwoWayStreamFun",
      requestType = com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest.class,
      responseType = com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest,
      com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> getTwoWayStreamFunMethod() {
    io.grpc.MethodDescriptor<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest, com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> getTwoWayStreamFunMethod;
    if ((getTwoWayStreamFunMethod = GreeterGrpc.getTwoWayStreamFunMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getTwoWayStreamFunMethod = GreeterGrpc.getTwoWayStreamFunMethod) == null) {
          GreeterGrpc.getTwoWayStreamFunMethod = getTwoWayStreamFunMethod = 
              io.grpc.MethodDescriptor.<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest, com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "grpc.Greeter", "TwoWayStreamFun"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("TwoWayStreamFun"))
                  .build();
          }
        }
     }
     return getTwoWayStreamFunMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreeterStub newStub(io.grpc.Channel channel) {
    return new GreeterStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreeterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreeterBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreeterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreeterFutureStub(channel);
  }

  /**
   * <pre>
   *服务端接口类
   * </pre>
   */
  public static abstract class GreeterImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *服务端接口方法
     * </pre>
     */
    public void sayHello(com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    /**
     * <pre>
     *服务器端流式rpc 1个请求多个返回
     * </pre>
     */
    public void serverSideStreamFun(com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getServerSideStreamFunMethod(), responseObserver);
    }

    /**
     * <pre>
     *客户端传入多个请求对象，服务端返回一个响应结果
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest> clientSideStreamFun(
        io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getClientSideStreamFunMethod(), responseObserver);
    }

    /**
     * <pre>
     *双向流式rpc 可以传入多个对象，返回多个响应对象
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest> twoWayStreamFun(
        io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getTwoWayStreamFunMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHelloMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest,
                com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply>(
                  this, METHODID_SAY_HELLO)))
          .addMethod(
            getServerSideStreamFunMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest,
                com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply>(
                  this, METHODID_SERVER_SIDE_STREAM_FUN)))
          .addMethod(
            getClientSideStreamFunMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest,
                com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply>(
                  this, METHODID_CLIENT_SIDE_STREAM_FUN)))
          .addMethod(
            getTwoWayStreamFunMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest,
                com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply>(
                  this, METHODID_TWO_WAY_STREAM_FUN)))
          .build();
    }
  }

  /**
   * <pre>
   *服务端接口类
   * </pre>
   */
  public static final class GreeterStub extends io.grpc.stub.AbstractStub<GreeterStub> {
    private GreeterStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterStub(channel, callOptions);
    }

    /**
     * <pre>
     *服务端接口方法
     * </pre>
     */
    public void sayHello(com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *服务器端流式rpc 1个请求多个返回
     * </pre>
     */
    public void serverSideStreamFun(com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getServerSideStreamFunMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *客户端传入多个请求对象，服务端返回一个响应结果
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest> clientSideStreamFun(
        io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getClientSideStreamFunMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *双向流式rpc 可以传入多个对象，返回多个响应对象
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest> twoWayStreamFun(
        io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getTwoWayStreamFunMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   *服务端接口类
   * </pre>
   */
  public static final class GreeterBlockingStub extends io.grpc.stub.AbstractStub<GreeterBlockingStub> {
    private GreeterBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *服务端接口方法
     * </pre>
     */
    public com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply sayHello(com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *服务器端流式rpc 1个请求多个返回
     * </pre>
     */
    public java.util.Iterator<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> serverSideStreamFun(
        com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getServerSideStreamFunMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *服务端接口类
   * </pre>
   */
  public static final class GreeterFutureStub extends io.grpc.stub.AbstractStub<GreeterFutureStub> {
    private GreeterFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *服务端接口方法
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply> sayHello(
        com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;
  private static final int METHODID_SERVER_SIDE_STREAM_FUN = 1;
  private static final int METHODID_CLIENT_SIDE_STREAM_FUN = 2;
  private static final int METHODID_TWO_WAY_STREAM_FUN = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreeterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreeterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply>) responseObserver);
          break;
        case METHODID_SERVER_SIDE_STREAM_FUN:
          serviceImpl.serverSideStreamFun((com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CLIENT_SIDE_STREAM_FUN:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.clientSideStreamFun(
              (io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply>) responseObserver);
        case METHODID_TWO_WAY_STREAM_FUN:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.twoWayStreamFun(
              (io.grpc.stub.StreamObserver<com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.HelloReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreeterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.grpc.model.protobuf.helloworld.HelloWorldServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Greeter");
    }
  }

  private static final class GreeterFileDescriptorSupplier
      extends GreeterBaseDescriptorSupplier {
    GreeterFileDescriptorSupplier() {}
  }

  private static final class GreeterMethodDescriptorSupplier
      extends GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GreeterMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreeterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreeterFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .addMethod(getServerSideStreamFunMethod())
              .addMethod(getClientSideStreamFunMethod())
              .addMethod(getTwoWayStreamFunMethod())
              .build();
        }
      }
    }
    return result;
  }
}

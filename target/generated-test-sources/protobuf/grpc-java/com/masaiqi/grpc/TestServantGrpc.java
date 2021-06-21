package com.masaiqi.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: test.proto")
public final class TestServantGrpc {

  private TestServantGrpc() {}

  public static final String SERVICE_NAME = "TestServant";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.masaiqi.grpc.Test,
      com.google.protobuf.Empty> METHOD_DO_TEST =
      io.grpc.MethodDescriptor.<com.masaiqi.grpc.Test, com.google.protobuf.Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "TestServant", "doTest"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.masaiqi.grpc.Test.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.google.protobuf.Empty.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TestServantStub newStub(io.grpc.Channel channel) {
    return new TestServantStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TestServantBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TestServantBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TestServantFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TestServantFutureStub(channel);
  }

  /**
   */
  public static abstract class TestServantImplBase implements io.grpc.BindableService {

    /**
     */
    public void doTest(com.masaiqi.grpc.Test request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DO_TEST, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_DO_TEST,
            asyncUnaryCall(
              new MethodHandlers<
                com.masaiqi.grpc.Test,
                com.google.protobuf.Empty>(
                  this, METHODID_DO_TEST)))
          .build();
    }
  }

  /**
   */
  public static final class TestServantStub extends io.grpc.stub.AbstractStub<TestServantStub> {
    private TestServantStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TestServantStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TestServantStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TestServantStub(channel, callOptions);
    }

    /**
     */
    public void doTest(com.masaiqi.grpc.Test request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DO_TEST, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TestServantBlockingStub extends io.grpc.stub.AbstractStub<TestServantBlockingStub> {
    private TestServantBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TestServantBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TestServantBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TestServantBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty doTest(com.masaiqi.grpc.Test request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DO_TEST, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TestServantFutureStub extends io.grpc.stub.AbstractStub<TestServantFutureStub> {
    private TestServantFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TestServantFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TestServantFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TestServantFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> doTest(
        com.masaiqi.grpc.Test request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DO_TEST, getCallOptions()), request);
    }
  }

  private static final int METHODID_DO_TEST = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TestServantImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TestServantImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DO_TEST:
          serviceImpl.doTest((com.masaiqi.grpc.Test) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class TestServantDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.masaiqi.grpc.TestOuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TestServantGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TestServantDescriptorSupplier())
              .addMethod(METHOD_DO_TEST)
              .build();
        }
      }
    }
    return result;
  }
}

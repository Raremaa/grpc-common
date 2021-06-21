package com.masaiqi;

import com.google.protobuf.Empty;
import com.masaiqi.grpc.ErrorInfo;
import com.masaiqi.grpc.Test;
import com.masaiqi.grpc.TestServantGrpc;
import io.grpc.*;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.List;

/**
 * 提供gRPC基本能力
 *
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
 * @date 2021/6/21 19:53
 */
public class BaseTemplate {

    private final int port = 9125;

    public void startServer(List<ServerInterceptor> serverInterceptors, List<BindableService> bindableServices) {
        var serverBuilder = NettyServerBuilder.forPort(port);

        if (bindableServices.size() > 0) {
            for (BindableService bindableService : bindableServices) {
                serverBuilder.addService(bindableService);
            }
        }

        if (serverInterceptors.size() > 0) {
            for (ServerInterceptor serverInterceptor : serverInterceptors) {
                serverBuilder.intercept(serverInterceptor);
            }
        }

        try {
            serverBuilder.build().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testResponse() {
        var channel = ManagedChannelBuilder.forAddress("127.0.0.1", port).usePlaintext().build();
        TestServantGrpc.newStub(channel).doTest(Test.getDefaultInstance(), new StreamObserver<Empty>() {
            @Override
            public void onNext(Empty value) {
                System.out.println("onNext");
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError");
                if (t instanceof StatusRuntimeException) {
                    StatusRuntimeException exception = StatusRuntimeException.class.cast(t);
                    Status status = exception.getStatus();
                    if (status.getCode() == Status.UNKNOWN.getCode()) {
                        ErrorInfo errorInfo = exception.getTrailers().get(ProtoUtils.keyForProto(ErrorInfo.getDefaultInstance()));
                        System.out.println("Got Error Code:" + errorInfo.toString());
                    }
                }
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        });

    }
}

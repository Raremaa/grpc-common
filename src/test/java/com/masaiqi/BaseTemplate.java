package com.masaiqi;

import com.masaiqi.grpc.Test;
import com.masaiqi.grpc.TestServantGrpc;
import io.grpc.BindableService;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;

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

    public void testResponse(io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        var channel = ManagedChannelBuilder.forAddress("127.0.0.1", port).usePlaintext().build();
        TestServantGrpc.newStub(channel).doTest(Test.getDefaultInstance(), responseObserver);

    }
}

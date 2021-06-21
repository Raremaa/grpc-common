package com.masaiqi.servant;

import com.google.protobuf.Empty;
import com.masaiqi.exception.BizException;
import com.masaiqi.grpc.Test;
import com.masaiqi.grpc.TestServantGrpc;
import com.masaiqi.server.StreamObserverDelegate;
import io.grpc.stub.StreamObserver;

/**
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
 * @date 2021/6/21 20:04
 */
public class TestServant extends TestServantGrpc.TestServantImplBase {

    @Override
    public void doTest(Test request, StreamObserver<Empty> responseObserver) {
        StreamObserverDelegate<Test, Empty> delegate = new StreamObserverDelegate<>(responseObserver);
        delegate.executeWithException(() -> {
            System.out.println("StreamObserverDelegate Done!");
            throw new BizException("0001");
        });
        super.doTest(request, responseObserver);
    }
}

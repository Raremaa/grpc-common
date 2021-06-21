package com.masaiqi.exception.handler;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

/**
 * Generic Throwable Handler
 *
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
 * @date 2021/6/9 20:16
 */
public class GenericThrowableHandler implements ThrowableHandler{

    @Override
    public boolean handle(Throwable throwable, StreamObserver streamObserver) {
        RuntimeException runtimeException = (RuntimeException) throwable;

        streamObserver.onError(Status.UNKNOWN
                .withDescription(runtimeException.getMessage())
                .withCause(runtimeException)
                .asRuntimeException());
        // 抛出异常让上层业务感知(比如事务回滚等可能要用到)
        throw runtimeException;
    }
}

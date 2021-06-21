package com.masaiqi.exception.handler;

import io.grpc.stub.StreamObserver;

/**
 * Throwable Handler interface.
 *
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
 * @date 2021/6/9 19:47
 */
public interface ThrowableHandler {

    /**
     * Handle Throwable
     *
     * @param throwable Throwable Object
     * @param streamObserver gRPC StreamObserver Object
     * @return false if you want to continue to execute next handler.
     * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
     * @date 2021-06-09 19:48
     */
    boolean handle(Throwable throwable, StreamObserver streamObserver);
}

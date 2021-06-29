package com.masaiqi.exception.strategy;

import io.grpc.stub.StreamObserver;

/**
 * Exception Handling Interface
 * <p>
 * Subclass should be thread-safe.
 *
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
 * @date 2021/6/29 19:29
 */
public interface ExceptionHandleStrategy {

    /**
     * Whether the current exception can be handled
     *
     * @param exception exception instance.
     * @return true if current class can Handle exception.
     * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
     * @date 2021-06-29 19:42
     */
    boolean canHandle(Exception exception);

    /**
     * Handle Exception
     *
     * @param exception exception instance.
     * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
     * @date 2021-06-29 19:46
     */
    void handleException(Exception exception, StreamObserver streamObserver);

}

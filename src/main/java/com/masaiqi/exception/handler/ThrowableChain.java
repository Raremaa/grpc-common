package com.masaiqi.exception.handler;

import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Chain Of Responsibility
 *
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
 * @date 2021/6/9 19:48
 */
public class ThrowableChain {

    private List<ThrowableHandler> handlers = new ArrayList<>();

    /**
     * Attention, Thread-unsafe.
     */
    public void addHandler(ThrowableHandler handler) {
        this.handlers.add(handler);
    }

    public void handle(Throwable throwable, StreamObserver streamObserver) {
        for (ThrowableHandler handler : handlers) {
            boolean handled = handler.handle(throwable, streamObserver);
            if (handled) {
                break;
            }
        }
    }
}

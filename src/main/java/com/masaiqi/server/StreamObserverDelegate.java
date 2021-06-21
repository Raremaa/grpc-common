package com.masaiqi.server;

import com.google.protobuf.Message;
import com.masaiqi.exception.handler.BizExceptionHandler;
import com.masaiqi.exception.handler.GenericThrowableHandler;
import com.masaiqi.exception.handler.ThrowableChain;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * gRPC回调委派(装饰)类，职责是增强原有的{@link StreamObserver}, 新增捕获gRPC的异常并执行相应的处理
 * <p>
 * 错误信息定义见 {@link com.masaiqi.grpc.Error} | error.proto
 * <p>
 * Thread-unSafe implementation
 *
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
 * @date 2021/6/2 22:44
 */
public class StreamObserverDelegate<ReqT extends Message, RespT extends Message> implements StreamObserver<RespT> {

    private static final Logger logger = LoggerFactory.getLogger(StreamObserverDelegate.class);

    private StreamObserver<RespT> originResponseObserver;

    private ThrowableChain throwableChain;

    public StreamObserverDelegate(StreamObserver<RespT> originResponseObserver) {
        if (originResponseObserver == null) {
            logger.error("originResponseObserver must not null!");
        }
        this.originResponseObserver = originResponseObserver;
        initThrowableChain();
    }

    private void initThrowableChain() {
        this.throwableChain = new ThrowableChain();
        this.throwableChain.addHandler(new BizExceptionHandler());
        this.throwableChain.addHandler(new GenericThrowableHandler());
    }

    @Override
    public void onNext(RespT value) {
        if (this.originResponseObserver != null) {
            this.originResponseObserver.onNext(value);
        }
    }

    @Override
    public void onError(Throwable t) {
        throwableChain.handle(t, originResponseObserver);
    }

    @Override
    public void onCompleted() {
        if (originResponseObserver != null) {
            originResponseObserver.onCompleted();
        }
    }

    /**
     * 执行业务(自动处理异常)
     *
     * @author masaiqi
     * @date 2021/4/12 18:11
     */
    public RespT executeWithException(Function<ReqT, RespT> function, ReqT request) {
        RespT response = null;
        try {
            response = function.apply(request);
        } catch (Throwable e) {
            this.onError(e);
        }
        return response;
    }

    /**
     * 执行业务(自动处理异常)
     *
     * @author masaiqi
     * @date 2021/4/12 18:11
     */
    public RespT executeWithException(Supplier<RespT> supplier) {
        RespT response = null;
        try {
            response = supplier.get();
        } catch (Throwable e) {
            this.onError(e);
        }
        return response;
    }

    /**
     * 执行业务(自动处理异常)
     *
     * @author masaiqi
     * @date 2021/4/12 18:11
     */
    public void executeWithException(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable e) {
            this.onError(e);
        }
    }
}

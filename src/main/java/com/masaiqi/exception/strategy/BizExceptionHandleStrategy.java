package com.masaiqi.exception.strategy;

import com.masaiqi.exception.BizException;
import com.masaiqi.grpc.ErrorInfo;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;

import java.util.Optional;

/**
 * Business exception handler
 *
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
 * @date 2021/6/29 19:47
 */
public class BizExceptionHandleStrategy implements ExceptionHandleStrategy{

    private final Metadata.Key<ErrorInfo> ERROR_INFO_TRAILER_KEY =
            ProtoUtils.keyForProto(ErrorInfo.getDefaultInstance());

    @Override
    public boolean canHandle(Exception exception) {
        return exception instanceof BizException;
    }

    @Override
    public void handleException(Exception exception, StreamObserver streamObserver) {
        // 业务异常，返回错误码和默认文案到客户端
        BizException bizException = (BizException) exception;
        Metadata trailers = new Metadata();
        ErrorInfo errorInfo = ErrorInfo.newBuilder()
                .setErrorCode(Optional.ofNullable(bizException.getErrorCode()).orElse(""))
                .setDefaultMsg(Optional.ofNullable(bizException.getMessage()).orElse(""))
                .build();
        trailers.put(ERROR_INFO_TRAILER_KEY, errorInfo);
        streamObserver.onError(Status.UNKNOWN
                .withCause(bizException)
                .asRuntimeException(trailers));
        // 抛出异常让当前业务感知
        throw bizException;
    }
}

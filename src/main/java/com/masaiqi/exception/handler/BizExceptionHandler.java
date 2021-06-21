package com.masaiqi.exception.handler;

import com.masaiqi.exception.BizException;
import com.masaiqi.grpc.ErrorInfo;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;

import java.util.Optional;

/**
 * Business Exception Handler.
 *
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
 * @date 2021/6/9 20:03
 */
public class BizExceptionHandler implements ThrowableHandler {

    Metadata.Key<ErrorInfo> ERROR_INFO_TRAILER_KEY =
            ProtoUtils.keyForProto(ErrorInfo.getDefaultInstance());

    @Override
    public boolean handle(Throwable throwable, StreamObserver streamObserver) {
        if (!(throwable instanceof BizException)) {
            return false;
        }

        // 业务异常，返回错误码和默认文案到客户端
        BizException bizException = (BizException) throwable;
        Metadata trailers = new Metadata();
        ErrorInfo errorInfo = ErrorInfo.newBuilder()
                .setErrorCode(Optional.ofNullable(bizException.getErrorCode()).orElse(""))
                .setDefaultMsg(Optional.ofNullable(bizException.getMessage()).orElse(""))
                .build();
        trailers.put(ERROR_INFO_TRAILER_KEY, errorInfo);
        streamObserver.onError(Status.UNKNOWN
                .withCause(bizException)
                .asRuntimeException(trailers));
        return true;
    }

}

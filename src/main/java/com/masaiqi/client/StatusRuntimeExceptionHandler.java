package com.masaiqi.client;

import com.masaiqi.grpc.ErrorInfo;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.ProtoUtils;

import java.util.Optional;

/**
 * Handler For {@link StatusRuntimeException}
 *
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>mpo
 * @date 2021/6/29 21:07
 */
public class StatusRuntimeExceptionHandler {

    private static final Metadata.Key<ErrorInfo> ERROR_INFO_TRAILER_KEY =
            ProtoUtils.keyForProto(ErrorInfo.getDefaultInstance());

    public static Optional<ErrorInfo> handle(StatusRuntimeException exception) {
        Status status = Status.fromThrowable(exception);
        if (status.getCode() == Status.UNKNOWN.getCode()) {
            ErrorInfo errorInfo = exception.getTrailers().get(ERROR_INFO_TRAILER_KEY);
            return Optional.of(errorInfo);
        }
        return Optional.empty();
    }
}

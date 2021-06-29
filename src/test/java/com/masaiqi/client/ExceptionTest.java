package com.masaiqi.client;

import com.google.protobuf.Empty;
import com.masaiqi.BaseTemplate;
import com.masaiqi.grpc.ErrorInfo;
import com.masaiqi.servant.TestServant;
import io.grpc.BindableService;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试异常处理
 *
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
 * @date 2021/6/21 19:51
 */
public class ExceptionTest extends BaseTemplate {

    @Test
    public void testExceptionHandler() throws InterruptedException {
        TestServant testServant = new TestServant();
        List<BindableService> servants = Stream.of(testServant).collect(Collectors.toList());
        this.startServer(Collections.emptyList(), servants);

        testResponse(new StreamObserver<Empty>() {
            @Override
            public void onNext(Empty value) {
                System.out.println("onNext");
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError");
                if (t instanceof StatusRuntimeException) {
                    StatusRuntimeException exception = StatusRuntimeException.class.cast(t);
                    Optional<ErrorInfo> handle = StatusRuntimeExceptionHandler.handle(exception);
                    handle.ifPresent(errorInfo -> {
                        System.out.print("ErrorCode:");
                        System.out.println(errorInfo.getErrorCode());
                        System.out.print("DefaultMsg:");
                        System.out.println(errorInfo.getDefaultMsg());
                    });
                }
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        });

        Thread.sleep(200000L);
    }

}

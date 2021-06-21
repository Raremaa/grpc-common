package com.masaiqi.client;

import com.masaiqi.BaseTemplate;
import com.masaiqi.servant.TestServant;
import io.grpc.BindableService;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
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
        testResponse();

        Thread.sleep(200000L);
    }

}

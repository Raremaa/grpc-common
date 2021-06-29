package com.masaiqi.exception.strategy;

/**
 * Simple Factory for {@link ExceptionHandleStrategy}
 *
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
 * @date 2021/6/29 20:06
 */
public class ExceptionHandleStrategyFactory {

    public static final BizExceptionHandleStrategy bizExceptionHandleStrategy = new BizExceptionHandleStrategy();
    public static final GenericExceptionHandlerStrategy genericExceptionHandlerStrategy = new GenericExceptionHandlerStrategy();

    public static ExceptionHandleStrategy getStrategy(Exception exception) {
        if (bizExceptionHandleStrategy.canHandle(exception)) {
            return bizExceptionHandleStrategy;
        }

        return genericExceptionHandlerStrategy;
    }
}

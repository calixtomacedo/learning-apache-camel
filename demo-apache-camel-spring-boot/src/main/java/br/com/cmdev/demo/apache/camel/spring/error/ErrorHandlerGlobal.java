package br.com.cmdev.demo.apache.camel.spring.error;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ErrorHandlerGlobal {

    private final Map<Class<?>, Function<Exception, ResponseError>> exceptionHandlers = new HashMap<>();

    private Function<Exception, ResponseError> defaultHandler;

    public <T extends Exception> void registerHandler(Class<T> clazz, Function<Exception, ResponseError> handler) {
        exceptionHandlers.put(clazz, handler);
    }

    public void registerDefaultHandler(Function<Exception, ResponseError> handler) {
        this.defaultHandler = handler;
    }

    public <T extends Exception> Function<Exception, ResponseError> getHandler(Class<T> clazz) {
        Function<Exception, ResponseError> handler = exceptionHandlers.get(clazz);
        if (handler != null) {
            return handler;
        }
        return defaultHandler;
    }

}

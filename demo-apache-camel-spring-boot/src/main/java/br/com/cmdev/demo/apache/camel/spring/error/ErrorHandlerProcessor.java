package br.com.cmdev.demo.apache.camel.spring.error;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.function.Function;

import static br.com.cmdev.demo.apache.camel.spring.router.RouterConstants.MAXIMUM_REDELIVERIES_EXCEEDED;

@Component
public class ErrorHandlerProcessor implements Processor {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandlerProcessor.class);
    private ErrorHandlerGlobal handlerGlobal;

    @Override
    public void process(Exchange exchange) throws Exception {
        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        Function<Exception, ResponseError> exceptionHander = handlerGlobal.getHandler(exception.getClass());
        ResponseError responseError = exceptionHander.apply(exception);
        if(isMaximumRedeliveriesExceeded(exchange)) {
            var errorMessage = "Código: ".concat(responseError.code().toString()).concat(" Messagem: ").concat(responseError.message());
            throw new Exception(errorMessage, exception);
        }
    }

    private boolean isMaximumRedeliveriesExceeded(Exchange exchange) {
        return Boolean.TRUE.equals(exchange.getProperty(MAXIMUM_REDELIVERIES_EXCEEDED, Boolean.class));
    }

    public ErrorHandlerProcessor() {
        this.handlerGlobal = new ErrorHandlerGlobal();
        this.handlerGlobal.registerDefaultHandler(this::handlerGeneric);
        this.handlerGlobal.registerHandler(HttpOperationFailedException.class, this::handlerHttp);
    }


    private ResponseError handlerGeneric(Exception exception) {
        log.error("Ocorreu um erro interno: {}", exception.getMessage(), exception);
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), null);
    }

    private ResponseError handlerHttp(Exception exception) {
        HttpOperationFailedException httpException = (HttpOperationFailedException) exception;
        log.error("{} ResponseBody: {}", httpException.getMessage(), httpException.getResponseBody() , exception);
        return new ResponseError(httpException.getStatusCode(), httpException.getMessage(), httpException.getResponseBody());
    }

}

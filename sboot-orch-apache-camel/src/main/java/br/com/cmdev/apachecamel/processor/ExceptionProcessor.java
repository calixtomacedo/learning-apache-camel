package br.com.cmdev.apachecamel.processor;

import br.com.cmdev.apachecamel.utils.ErrorResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class ExceptionProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        var exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
        var operationFailedException = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, HttpOperationFailedException.class);
        int statusCode;
        if(exception instanceof HttpOperationFailedException){
            statusCode = ((HttpOperationFailedException) exception).getStatusCode();
        }else {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, statusCode);
        var errorResponse = ErrorResponse.builder().code(statusCode).message(exception.getMessage()).build();
        exchange.getIn().setBody(errorResponse);
    }
}

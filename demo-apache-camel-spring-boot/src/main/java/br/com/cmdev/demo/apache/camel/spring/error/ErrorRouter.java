package br.com.cmdev.demo.apache.camel.spring.error;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static br.com.cmdev.demo.apache.camel.spring.router.RouterConstants.*;

@Component
public class ErrorRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from(ERRO_ROUTER_NAME)
                .routeId(ERRO_ROUTER_ID)
                .description(ERROR_ROUTER_DESCRIPTION)
                .process(new ErrorHandlerProcessor())
        ;

    }
}

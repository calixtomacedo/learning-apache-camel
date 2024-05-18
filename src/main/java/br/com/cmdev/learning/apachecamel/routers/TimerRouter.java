package br.com.cmdev.learning.apachecamel.routers;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

/*
        from("timer:myFirstTimerCamel?period=5000")
                .routeId("my-first-timer-id")
                .log("Data e hora agora: ${date:now:dd/MM/yyyy HH:mm:ss}")
                .end();

 */
    }


}

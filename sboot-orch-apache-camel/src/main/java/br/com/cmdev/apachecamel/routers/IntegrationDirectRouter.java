package br.com.cmdev.apachecamel.routers;
/*
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpMethods;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class IntegrationDirectRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:people")
                .routeId("route-people-id")
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                .toD("http://localhost:8081/people")
                .process(exchange -> {
                    System.out.println(exchange.getIn().getBody());
                })
                .end();

    }

}

 */

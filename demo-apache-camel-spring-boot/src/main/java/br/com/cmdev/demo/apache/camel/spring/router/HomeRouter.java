package br.com.cmdev.demo.apache.camel.spring.router;

import br.com.cmdev.demo.apache.camel.spring.rest.DemoApi;
import org.springframework.stereotype.Component;

import static br.com.cmdev.demo.apache.camel.spring.router.RouterConstants.HOME_ROUTER_ID;
import static br.com.cmdev.demo.apache.camel.spring.router.RouterConstants.HOME_ROUTER_NAME;
import static br.com.cmdev.demo.apache.camel.spring.router.product.ProductRouter.PRODUCT_ROUTE;

@Component
public class HomeRouter extends DemoApi {

    @Override
    public void configure() throws Exception {
/*
        onException(HttpOperationFailedException.class)
                .maximumRedeliveries(MAXIMUM_REDELIVERIES)
                .redeliveryDelay(REDELIVERIES_DELAY)
                .handled(false)
                .asyncDelayedRedelivery()
                .onRedelivery(new RedeliveryProcessor(HOME_ROUTER_ID))
        ;
*/
        from(HOME_ROUTER_NAME)
                .routeId(HOME_ROUTER_ID)
                .log("Código produto recebido: ${header.productId}")
                .to(PRODUCT_ROUTE.uri())
                .end()
        ;
    }
}

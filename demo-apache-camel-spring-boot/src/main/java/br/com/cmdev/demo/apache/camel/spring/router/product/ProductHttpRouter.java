package br.com.cmdev.demo.apache.camel.spring.router.product;

import br.com.cmdev.demo.apache.camel.spring.router.RouterProperties;
import br.com.cmdev.demo.apache.camel.spring.error.domain.RedeliveryProcessor;
import br.com.cmdev.demo.apache.camel.spring.util.CamelRouteFactory;
import br.com.cmdev.demo.apache.camel.spring.util.HttpIntegrationRouterFactory;
import br.com.cmdev.demo.apache.camel.spring.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import static br.com.cmdev.demo.apache.camel.spring.router.RouterConstants.*;
import static br.com.cmdev.demo.apache.camel.spring.router.RouterConstants.ERRO_ROUTER_NAME;

@Component
@EnableConfigurationProperties(RouterProperties.class)
public class ProductHttpRouter extends RouteBuilder {

    public static final CamelRouteFactory<Integer, Product> PRODUCT_HTTP_ROUTE = CamelRouteFactory.createDirect("product-http-route", Integer.class, Product.class);

    private final ObjectMapper objectMapper;
    private final RouterProperties routerProperties;

    public ProductHttpRouter(ObjectMapper objectMapper, RouterProperties routerProperties) {
        this.objectMapper = objectMapper;
        this.routerProperties = routerProperties;
    }

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .maximumRedeliveries(MAXIMUM_REDELIVERIES)
                .redeliveryDelay(REDELIVERIES_DELAY)
                .handled(false)
                .asyncDelayedRedelivery()
                .onRedelivery(new RedeliveryProcessor(PRODUCT_HTTP_ROUTE.id()))
                .to(ERRO_ROUTER_NAME)
        ;

        HttpIntegrationRouterFactory.createGetRestJsonIntegration(
                objectMapper,
                this.from(PRODUCT_HTTP_ROUTE.uri()),
                routerProperties.productUrl().concat(PRODUCT_URI).concat(PROP_HEADER_PRODUCT_ID),
                Product.class
        );

    }
}

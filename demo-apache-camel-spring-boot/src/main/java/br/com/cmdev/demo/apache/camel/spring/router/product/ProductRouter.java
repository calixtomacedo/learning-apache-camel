package br.com.cmdev.demo.apache.camel.spring.router.product;

import br.com.cmdev.demo.apache.camel.spring.util.CamelRouteFactory;
import br.com.cmdev.demo.apache.camel.spring.domain.Product;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static br.com.cmdev.demo.apache.camel.spring.router.product.ProductHttpRouter.PRODUCT_HTTP_ROUTE;

@Component
public class ProductRouter extends RouteBuilder {

    public static final CamelRouteFactory<Integer, Product> PRODUCT_ROUTE = CamelRouteFactory.createDirect("product-route", Integer.class, Product.class);

    @Override
    public void configure() throws Exception {

        from(PRODUCT_ROUTE.uri())
                .routeId(PRODUCT_ROUTE.id())
                .to(PRODUCT_HTTP_ROUTE.uri())
                .end()
        ;
    }
}

package br.com.cmdev.demo.apache.camel.spring.rest;

import br.com.cmdev.demo.apache.camel.spring.domain.Product;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import static br.com.cmdev.demo.apache.camel.spring.router.RouterConstants.*;

@Component
public class DemoApi extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        rest()
                .get(GET_PRODUCT_ROUTER)
                .id(ID_PRODUCT_ROUTER)
                .tag(TAG_PRODUCT_ROUTER)
                .description(DESCRIPTION_PRODUCT_ROUTER)
                .produces(MEDIA_TYPE_APPLICATION_JSON)
                .outType(Product.class)
                .consumes(MEDIA_TYPE_APPLICATION_JSON)
                .type(Integer.class)
                .param()
                    .name(PROP_PARAM_PRODUCT_ID)
                    .type(RestParamType.path)
                    .required(true)
                .endParam()
                .to(HOME_ROUTER_NAME)
        ;
    }
}
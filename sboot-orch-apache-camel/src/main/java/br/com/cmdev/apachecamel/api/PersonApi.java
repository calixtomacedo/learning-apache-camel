package br.com.cmdev.apachecamel.api;

import br.com.cmdev.apachecamel.dto.PersonResponse;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

@Component
public class PersonApi extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        rest()
                .get("/v1/cmdev/person/{id}")
                .tag("getPerson")
                .description("Camel to get an person by id")
                .id("getPersonApi")
                .produces("application/json")
                .outType(PersonResponse.class)
                .param()
                    .name("id")
                    .type(RestParamType.path)
                    .required(true)
                    .description("Person code")
                .endParam()
                .to("direct:getPerson")
        ;
    }
}

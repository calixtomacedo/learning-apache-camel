package br.com.cmdev.apachecamel.api;

import br.com.cmdev.apachecamel.dto.Person;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

@Component
public class PersonApi extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        rest()
                .bindingMode(RestBindingMode.json)
                .get("/v1/cmdev/person/{id}")
                .tag("getPerson")
                .description("Camel to get an person by id")
                .id("getPersonApi")
                .consumes("application/json")
                .produces("application/json")
                .outType(Person[].class)
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

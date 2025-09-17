package br.com.cmdev.apachecamel.router;

import br.com.cmdev.apachecamel.config.RouterPropertiesConfig;
import br.com.cmdev.apachecamel.dto.Address;
import br.com.cmdev.apachecamel.processor.AddressProcessor;
import br.com.cmdev.apachecamel.utils.RouterConstants;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.ValueBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.apache.camel.model.dataformat.JsonLibrary.Jackson;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(RouterPropertiesConfig.class)
public class AddressRouter extends RouteBuilder {

    private final RouterPropertiesConfig properties;

    @Override

    public void configure() throws Exception {

        from(RouterConstants.ROUTE_ADDRESS)
                .routeId("getAddressPersonApiId")
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethod.GET))
                .log(LoggingLevel.INFO, "CONSULTA ENDEREÇO | Integração com o serviço: "+ properties.getAddressUrl().replace(RouterConstants.REQUEST_PARAM_PERSON_ID, RouterConstants.HEADER_PARAM_ID))
                .toD(properties.getAddressUrl().replace(RouterConstants.REQUEST_PARAM_PERSON_ID, RouterConstants.HEADER_PARAM_ID))
                .choice()
                    .when(header(Exchange.HTTP_RESPONSE_CODE).isEqualTo(HttpStatus.OK.value()))
                        .unmarshal().json(Jackson, List.class)
                        .process(new AddressProcessor())
                    .otherwise()
                        .process(exchange -> exchange.setProperty(RouterConstants.PROPERTY_BODY_ADDRESS, new ArrayList<Address>()))
                    .endChoice()
                .end()
        ;

    }
}

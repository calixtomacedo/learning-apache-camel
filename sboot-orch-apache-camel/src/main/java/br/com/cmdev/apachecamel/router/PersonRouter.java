package br.com.cmdev.apachecamel.router;

import br.com.cmdev.apachecamel.api.PersonApi;
import br.com.cmdev.apachecamel.config.RouterPropertiesConfig;
import br.com.cmdev.apachecamel.dto.Person;
import br.com.cmdev.apachecamel.processor.ExceptionProcessor;
import br.com.cmdev.apachecamel.processor.PersonProcessor;
import br.com.cmdev.apachecamel.utils.RouterConstants;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.dataformat.JsonLibrary.Jackson;


@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(RouterPropertiesConfig.class)
public class PersonRouter extends PersonApi {

    private final RouterPropertiesConfig properties;

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .process(new ExceptionProcessor())
                .end()
        ;

        from(RouterConstants.ROUTE_PERSON)
                .routeId("getPersonApiId")
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethod.GET))
                .removeHeader(Exchange.HTTP_URI)
                .to(RouterConstants.ROUTE_ADDRESS)
                .log(LoggingLevel.INFO, "CONSULTA PESSOA | Integração com o serviço: "+ properties.getPersonUrl().replace(RouterConstants.REQUEST_PARAM_PERSON_ID, RouterConstants.HEADER_PARAM_ID))
                .toD(properties.getPersonUrl().replace(RouterConstants.REQUEST_PARAM_PERSON_ID, RouterConstants.HEADER_PARAM_ID))
                .choice()
                    .when(header(Exchange.HTTP_RESPONSE_CODE).isEqualTo(HttpStatus.OK.value()))
                        .unmarshal().json(Jackson, Person.class)
                        .process(new PersonProcessor())
                    .endChoice()
                .end()
        ;

    }
}

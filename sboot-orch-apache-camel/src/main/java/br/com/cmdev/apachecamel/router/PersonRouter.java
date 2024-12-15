package br.com.cmdev.apachecamel.router;

import br.com.cmdev.apachecamel.api.PersonApi;
import br.com.cmdev.apachecamel.config.RouterPropertiesConfig;
import br.com.cmdev.apachecamel.dto.Address;
import br.com.cmdev.apachecamel.dto.Person;
import br.com.cmdev.apachecamel.processor.AddressProcessor;
import br.com.cmdev.apachecamel.processor.ExceptionProcessor;
import br.com.cmdev.apachecamel.processor.PersonProcessor;
import br.com.cmdev.apachecamel.utils.RouterConstants;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;


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
                .toD(properties.getPersonUrl().replace(RouterConstants.REQUEST_PARAM_PERSON_ID, RouterConstants.HEADER_PARAM_ID))
                .unmarshal(new JacksonDataFormat(Person.class))
                .process(new PersonProcessor())
                .end()
        ;

        from(RouterConstants.ROUTE_ADDRESS)
                .routeId("getAddressPerson")
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethod.GET))
                .toD(properties.getAddressUrl().replace(RouterConstants.REQUEST_PARAM_PERSON_ID, RouterConstants.HEADER_PARAM_ID))
                .unmarshal(new ListJacksonDataFormat(Address.class))
                .process(new AddressProcessor())
                .end()
        ;

    }
}

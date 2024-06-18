package br.com.cmdev.apachecamel.router;

import br.com.cmdev.apachecamel.api.PersonApi;
import br.com.cmdev.apachecamel.config.RouterPropertiesConfig;
import br.com.cmdev.apachecamel.dto.PersonResponse;
import br.com.cmdev.apachecamel.processor.PersonProcessor;
import br.com.cmdev.apachecamel.utils.RouterConstants;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import static org.apache.camel.LoggingLevel.INFO;

import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@EnableConfigurationProperties(RouterPropertiesConfig.class)
public class PersonRouter extends PersonApi {

    private final RouterPropertiesConfig properties;

    @Override
    public void configure() throws Exception {

        from(RouterConstants.ROUTE_PERSON)
                .routeId("getPersonApiId")
                .removeHeader(Exchange.HTTP_URI)
                .marshal(new JacksonDataFormat(PersonResponse.class))
                .toD(properties.getPersonUrl().replace(RouterConstants.REQUEST_PARAM_PERSON_ID, RouterConstants.HEADER_PARAM_ID))
                .unmarshal(new JacksonDataFormat(PersonResponse.class))
                .to(RouterConstants.ROUTE_ADDRESS)
                .end()
        ;

        from(RouterConstants.ROUTE_ADDRESS)
                .routeId("getAddressPerson")
                .log(INFO, this.log, "==> meu body ${body.idPerson}")
                .marshal(new JacksonDataFormat(PersonResponse.class))
                .toD(properties.getAddressUrl().replace(RouterConstants.REQUEST_PARAM_PERSON_ID, RouterConstants.BOBY_ID_PERSON))
                .unmarshal(new JacksonDataFormat(PersonResponse.class))
                .process(new PersonProcessor())
                .end()
        ;

    }
}

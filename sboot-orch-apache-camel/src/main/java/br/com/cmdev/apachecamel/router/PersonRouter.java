package br.com.cmdev.apachecamel.router;

import br.com.cmdev.apachecamel.api.PersonApi;
import br.com.cmdev.apachecamel.config.RouterPropertiesConfig;
import br.com.cmdev.apachecamel.dto.PersonResponse;
import br.com.cmdev.apachecamel.processor.PersonProcessor;
import br.com.cmdev.apachecamel.strategy.PersonAggregationStrategy;
import br.com.cmdev.apachecamel.utils.RouterConstants;
import lombok.RequiredArgsConstructor;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import static org.apache.camel.LoggingLevel.INFO;

import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
                .toD(properties.getPersonUrl().replace(RouterConstants.REQUEST_PARAM_PERSON_ID, RouterConstants.HEADER_PARAM_ID))
                .unmarshal(new JacksonDataFormat(PersonResponse.class))
                .log(INFO, this.log, "==> meu body1 ${body}")
                .split(simple("${body}"), new PersonAggregationStrategy()).parallelProcessing()
                .to(RouterConstants.ROUTE_ADDRESS)
                .end()
        ;

        from(RouterConstants.ROUTE_ADDRESS)
                .routeId("getAddressPerson")
                //.marshal(new JacksonDataFormat(PersonResponse.class))
                .log(INFO, this.log, "==> meu body2 ${body}")
                .toD(properties.getAddressUrl().replace(RouterConstants.REQUEST_PARAM_PERSON_ID, RouterConstants.BOBY_ID_PERSON))
                //.process(new PersonProcessor())
                .unmarshal(new JacksonDataFormat(PersonResponse.class))
                .log(INFO, this.log, "==> meu body3 ${body}")
                .end()
        ;

    }
}

package br.com.cmdev.apachecamel.router;

import br.com.cmdev.apachecamel.api.PersonApi;
import br.com.cmdev.apachecamel.config.RouterPropertiesConfig;
import br.com.cmdev.apachecamel.dto.PersonResponse;
import br.com.cmdev.apachecamel.utils.RouterConstants;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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
                .toD(properties.getPersonUrl().replace(RouterConstants.REQUEST_PARAM_PERSON_ID, RouterConstants.HEADER_PARAM_ID))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String response = exchange.getIn().getBody(String.class);
                        System.out.println(response);
                        exchange.getIn().setHeader("idPerson", response);

                    }
                })
                //.to(RouterConstants.ROUTE_ADDRESS)
                .end()
        ;

        from(RouterConstants.ROUTE_ADDRESS)
                .routeId("getAddressPerson")
                .log("OI")
                //.toD(properties.getAddressUrl().replace(RouterConstants.REQUEST_PARAM_ADDRESS_ID, RouterConstants.HEADER_PARAM_ID))

                .end()
        ;

    }
}

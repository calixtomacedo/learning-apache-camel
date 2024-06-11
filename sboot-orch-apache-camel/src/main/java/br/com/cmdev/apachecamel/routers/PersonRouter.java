package br.com.cmdev.apachecamel.routers;

import br.com.cmdev.apachecamel.api.PersonApi;
import br.com.cmdev.apachecamel.config.RouterPropertiesConfig;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@EnableConfigurationProperties(RouterPropertiesConfig.class)
public class PersonRouter extends PersonApi {

    private final RouterPropertiesConfig routerConfig;

    @Override
    public void configure() throws Exception {

        from("direct:getPerson")
                .routeId("getPersonApiId")
                .log("Chegou até aqui ======>")
                .toD(routerConfig.getPersonUrl().replace("{idPerson}", "1"))
                .log("Antes do end ======>")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().getBody(String.class);
                    }
                })

                .end()
        ;

        from("direct:teste")
                .log("OIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII")
                .end()
        ;

    }
}

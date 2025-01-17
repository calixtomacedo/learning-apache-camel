package br.com.cmdev.apachecamel.processor;

import br.com.cmdev.apachecamel.dto.Person;
import br.com.cmdev.apachecamel.utils.RouterConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;

@Slf4j
public class PersonProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        var personResponse = exchange.getIn().getBody(Person.class);
        var addressResponse = exchange.getProperty(RouterConstants.PROPERTY_BODY_ADDRESS, List.class);
        personResponse.setAddress(addressResponse);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonRequestGetPesron = objectWriter.writeValueAsString(personResponse);
        log.info("CONSULTA PESSOA | Consultando os dados da pessoa com o request: {}", jsonRequestGetPesron);
    }
}

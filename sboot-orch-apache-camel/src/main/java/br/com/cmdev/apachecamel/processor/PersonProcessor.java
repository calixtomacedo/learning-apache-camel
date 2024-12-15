package br.com.cmdev.apachecamel.processor;

import br.com.cmdev.apachecamel.dto.Person;
import br.com.cmdev.apachecamel.utils.RouterConstants;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;

public class PersonProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        var personResponse = exchange.getIn().getBody(Person.class);
        var addressResponse = exchange.getProperty(RouterConstants.PROPERTY_BODY_ADDRESS, List.class);
        personResponse.setAddress(addressResponse);
    }
}

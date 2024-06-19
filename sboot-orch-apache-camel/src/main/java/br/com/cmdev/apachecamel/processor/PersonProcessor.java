package br.com.cmdev.apachecamel.processor;

import br.com.cmdev.apachecamel.dto.PersonResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.ArrayList;

public class PersonProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        var person = exchange.getIn().getBody(PersonResponse.class);
        person.setMeuAtributo("Olá Calixto");
        person.setAddresses(new ArrayList<>());
        exchange.getIn().setBody(person);
    }
}

package br.com.cmdev.apachecamel.processor;

import br.com.cmdev.apachecamel.dto.PersonResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PersonProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        var response = exchange.getIn().getBody();
        //response.setMeuAtributo("Olá Calixto");
    }
}

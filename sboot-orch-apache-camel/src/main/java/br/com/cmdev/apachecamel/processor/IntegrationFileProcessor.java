package br.com.cmdev.apachecamel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class IntegrationFileProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getMessage().getBody(String.class));
    }
}

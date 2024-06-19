package br.com.cmdev.apachecamel.strategy;

import br.com.cmdev.apachecamel.dto.Address;
import br.com.cmdev.apachecamel.dto.PersonResponse;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.List;

public class PersonAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        var person = newExchange.getIn().getBody(PersonResponse.class);
        List<Address> addresses = List.of();
        if (oldExchange == null) {
            addresses = new ArrayList<>();
        }
        addresses.add(new Address());
        person.setMeuAtributo("Meu Atributo");
        person.setAddresses(addresses);
        System.out.println("PASSOU AQUI.................");
        return newExchange;
    }
}

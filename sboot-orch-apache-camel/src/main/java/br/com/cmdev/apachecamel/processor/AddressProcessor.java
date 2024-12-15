package br.com.cmdev.apachecamel.processor;

import br.com.cmdev.apachecamel.utils.RouterConstants;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;

public class AddressProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        var addressList = exchange.getIn().getBody(List.class);
        exchange.setProperty(RouterConstants.PROPERTY_BODY_ADDRESS, addressList);
    }
}

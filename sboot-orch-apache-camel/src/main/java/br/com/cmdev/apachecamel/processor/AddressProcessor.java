package br.com.cmdev.apachecamel.processor;

import br.com.cmdev.apachecamel.utils.RouterConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;

@Slf4j
public class AddressProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        var addressList = exchange.getIn().getBody(List.class);
        exchange.setProperty(RouterConstants.PROPERTY_BODY_ADDRESS, addressList);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonAddressesResponse = objectWriter.writeValueAsString(addressList);
        log.info("CONSULTA ENDEREÇOS | Resultado da consulta endereços: {}", jsonAddressesResponse);
    }
}

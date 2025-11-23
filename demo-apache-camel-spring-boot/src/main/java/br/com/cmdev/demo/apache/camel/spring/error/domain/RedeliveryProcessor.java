package br.com.cmdev.demo.apache.camel.spring.error.domain;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static br.com.cmdev.demo.apache.camel.spring.router.RouterConstants.MAXIMUM_REDELIVERIES;
import static br.com.cmdev.demo.apache.camel.spring.router.RouterConstants.MAXIMUM_REDELIVERIES_EXCEEDED;

public class RedeliveryProcessor implements Processor {

    private static final Logger log = LoggerFactory.getLogger(RedeliveryProcessor.class);
    private final String processor;

    public RedeliveryProcessor(String processor) {
        this.processor = processor;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        var redeliveriesAmount = exchange.getIn().getHeader(Exchange.REDELIVERY_COUNTER, Integer.class);
        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        log.warn("Retentativa {} do processo {}, mensagem de erro recebido: ", redeliveriesAmount, processor, exception.getMessage());

        if (redeliveriesAmount >= MAXIMUM_REDELIVERIES) {
            log.error("Limite de tentativas {} excedido.", processor);
            exchange.setProperty(MAXIMUM_REDELIVERIES_EXCEEDED, true);
        }
    }
}

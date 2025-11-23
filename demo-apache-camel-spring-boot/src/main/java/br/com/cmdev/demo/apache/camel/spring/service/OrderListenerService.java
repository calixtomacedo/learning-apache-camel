package br.com.cmdev.demo.apache.camel.spring.service;

import br.com.cmdev.demo.apache.camel.spring.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class OrderListenerService {

    private static final Logger log = LoggerFactory.getLogger(OrderListenerService.class);
    private final PubSubTemplate pubSubTemplate;
    private final ObjectMapper objectMapper;
    private final String topicName;

    public OrderListenerService(PubSubTemplate pubSubTemplate, ObjectMapper objectMapper, @Value("${spring.cloud.gcp.topic-name}") String topicName) {
        this.pubSubTemplate = pubSubTemplate;
        this.objectMapper = objectMapper;
        this.topicName = topicName;
    }

    @PostConstruct
    public void listenerPubSubTopic() {
        pubSubTemplate.subscribe(topicName, message -> {
            try {
                var dataMessage = new String(message.getPubsubMessage().getData().toByteArray());
                var order = objectMapper.readValue(dataMessage, Order.class);
                log.info("Mensagem: {}, recebida do topic: {}", order, topicName);
                message.ack();
            } catch (Exception ex) {
                log.error("Erro ao consumir a mensagem do tópico", ex.getMessage(), ex);
                message.nack();
            }
        });

    }
}

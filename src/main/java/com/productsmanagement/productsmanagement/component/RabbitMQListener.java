package com.productsmanagement.productsmanagement.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productsmanagement.productsmanagement.config.RabbitMQConfig;
import com.productsmanagement.productsmanagement.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void listen(ProductDTO product) {
        try {
            String productJson = objectMapper.writeValueAsString(product);
            logger.info("Mensagem recebida: {}", productJson);
        } catch (JsonProcessingException e) {
            logger.error("Erro ao converter ProductDTO para JSON", e);
        }
    }

}

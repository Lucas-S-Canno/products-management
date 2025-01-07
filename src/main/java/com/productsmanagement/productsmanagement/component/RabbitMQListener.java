package com.productsmanagement.productsmanagement.component;

import com.productsmanagement.productsmanagement.config.RabbitMQConfig;
import com.productsmanagement.productsmanagement.dto.ProductDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void listen(ProductDTO product) {
        System.out.println("Mensagem recebida: " + product);
    }

}

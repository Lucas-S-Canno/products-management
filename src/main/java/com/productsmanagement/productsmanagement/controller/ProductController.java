package com.productsmanagement.productsmanagement.controller;

import com.productsmanagement.productsmanagement.component.RabbitMQSender;
import com.productsmanagement.productsmanagement.config.RabbitMQConfig;
import com.productsmanagement.productsmanagement.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    //criando a base para guardar informações
    private final Map<Long, ProductDTO> productRepository = new HashMap<>();
    private Long productIdSequence = 1L;

    private final RabbitMQSender rabbitMQSender;

    @Autowired
    public ProductController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    //Endpoint para criar um produto
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody ProductDTO productDTO
    ) {
        productDTO.setId(productIdSequence++);
        productRepository.put(productDTO.getId(), productDTO);
        rabbitMQSender.sendMessage(RabbitMQConfig.QUEUE_NAME, productDTO);
        return ResponseEntity.ok(productDTO);
    }

    //Endpoint para buscar todos os produtos
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(new ArrayList<>(productRepository.values()));
    }

    //Endpoint para atualizar um produto
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO
    ) {
        if (!productRepository.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        productDTO.setId(id);
        productRepository.put(id, productDTO);
        return ResponseEntity.ok(productDTO);
    }

    //Endpoint para deletar um produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ) {
        if (!productRepository.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.remove(id);
        return ResponseEntity.noContent().build();
    }

}

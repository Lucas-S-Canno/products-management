package com.productsmanagement.productsmanagement.controller;

import com.productsmanagement.productsmanagement.component.RabbitMQSender;
import com.productsmanagement.productsmanagement.dto.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private RabbitMQSender rabbitMQSender;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Laptop");
        productDTO.setDescription("High-end gaming laptop");
        productDTO.setPrice(1500.00);
        productDTO.setQuantity(10);
        productDTO.setCategory("Electronics");

        ResponseEntity<ProductDTO> response = productController.createProduct(productDTO);

        assertNotNull(response.getBody());
        assertEquals("Laptop", response.getBody().getName());
        verify(rabbitMQSender, times(1)).sendMessage(anyString(), any(ProductDTO.class));
    }

    @Test
    void testGetAllProducts() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Laptop");
        productController.createProduct(productDTO);

        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();

        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void testUpdateProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Laptop");
        productController.createProduct(productDTO);

        ProductDTO updatedProductDTO = new ProductDTO();
        updatedProductDTO.setName("Updated Laptop");
        updatedProductDTO.setDescription("Updated description");
        updatedProductDTO.setPrice(2000.00);
        updatedProductDTO.setQuantity(5);
        updatedProductDTO.setCategory("Updated Electronics");

        ResponseEntity<ProductDTO> response = productController.updateProduct(1L, updatedProductDTO);

        assertNotNull(response.getBody());
        assertEquals("Updated Laptop", response.getBody().getName());
    }

    @Test
    void testDeleteProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Laptop");
        productController.createProduct(productDTO);

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(204, response.getStatusCodeValue());
    }
}
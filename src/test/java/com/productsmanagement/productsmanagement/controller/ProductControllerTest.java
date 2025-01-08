package com.productsmanagement.productsmanagement.controller;

import com.productsmanagement.productsmanagement.component.RabbitMQSender;
import com.productsmanagement.productsmanagement.dto.ProductDTO;
import com.productsmanagement.productsmanagement.service.ProductService;
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

    @Mock
    private ProductService productService;

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

        when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.createProduct(productDTO);

        assertNotNull(response.getBody());
        assertEquals("Laptop", response.getBody().getName());
        verify(productService, times(1)).createProduct(any(ProductDTO.class));
        verify(rabbitMQSender, times(1)).sendMessage(anyString(), any(ProductDTO.class));
    }

    @Test
    void testGetAllProducts() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Laptop");

        when(productService.getAllProducts()).thenReturn(List.of(productDTO));

        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();

        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
        assertEquals("Laptop", response.getBody().get(0).getName());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testUpdateProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Updated Laptop");
        productDTO.setDescription("Updated description");
        productDTO.setPrice(2000.00);
        productDTO.setQuantity(5);
        productDTO.setCategory("Updated Electronics");

        when(productService.updateProduct(eq(1L), any(ProductDTO.class))).thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.updateProduct(1L, productDTO);

        assertNotNull(response.getBody());
        assertEquals("Updated Laptop", response.getBody().getName());
        verify(productService, times(1)).updateProduct(eq(1L), any(ProductDTO.class));
    }

    @Test
    void testDeleteProduct() {
        when(productService.deleteProduct(1L)).thenReturn(true);

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(204, response.getStatusCode().value());
        verify(productService, times(1)).deleteProduct(1L);
    }
}
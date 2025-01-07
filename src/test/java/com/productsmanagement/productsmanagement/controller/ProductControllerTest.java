package com.productsmanagement.productsmanagement.controller;

import com.productsmanagement.productsmanagement.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Test
    void productDTOShouldHaveCorrectValues() {
        ProductDTO product = new ProductDTO(1L, "Laptop", "High-end gaming laptop", 1500.00, 10, "Electronics");
        assertEquals(1L, product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals("High-end gaming laptop", product.getDescription());
        assertEquals(1500.00, product.getPrice());
        assertEquals(10, product.getQuantity());
        assertEquals("Electronics", product.getCategory());
    }

    @Test
    void productDTOShouldHandleNullValues() {
        ProductDTO product = new ProductDTO(null, null, null, null, null, null);
        assertNull(product.getId());
        assertNull(product.getName());
        assertNull(product.getDescription());
        assertNull(product.getPrice());
        assertNull(product.getQuantity());
        assertNull(product.getCategory());
    }

    @Test
    void productDTOShouldUpdateValues() {
        ProductDTO product = new ProductDTO();
        product.setId(2L);
        product.setName("Smartphone");
        product.setDescription("Latest model smartphone");
        product.setPrice(800.00);
        product.setQuantity(20);
        product.setCategory("Electronics");

        assertEquals(2L, product.getId());
        assertEquals("Smartphone", product.getName());
        assertEquals("Latest model smartphone", product.getDescription());
        assertEquals(800.00, product.getPrice());
        assertEquals(20, product.getQuantity());
        assertEquals("Electronics", product.getCategory());
    }

}

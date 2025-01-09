package com.productsmanagement.productsmanagement.service;

import com.productsmanagement.productsmanagement.dto.ProductDTO;
import com.productsmanagement.productsmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        return productRepository.save(productDTO);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        if (!productRepository.existsById(id)) {
            return null;
        }
        productDTO.setId(id);
        return productRepository.save(productDTO);
    }

    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}

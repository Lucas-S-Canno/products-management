package com.productsmanagement.productsmanagement.service;

import com.productsmanagement.productsmanagement.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    //Service criado para separar as regras de negócio que estavam no controller
    //assim facilitando na manutenção do código e na organização

    //criando a base para guardar informações
    private final Map<Long, ProductDTO> productRepository = new HashMap<>();
    private Long productIdSequence = 1L;

    public ProductDTO createProduct(ProductDTO productDTO) {
        productDTO.setId(productIdSequence++);
        productRepository.put(productDTO.getId(), productDTO);
        return productDTO;
    }

    public List<ProductDTO> getAllProducts() {
        return new ArrayList<>(productRepository.values());
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        if (!productRepository.containsKey(id)) {
            return null;
        }
        productDTO.setId(id);
        productRepository.put(id, productDTO);
        return productDTO;
    }

    public boolean deleteProduct(Long id) {
        if (!productRepository.containsKey(id)) {
            return false;
        }
        productRepository.remove(id);
        return true;
    }
}

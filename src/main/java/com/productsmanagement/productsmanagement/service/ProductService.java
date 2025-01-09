package com.productsmanagement.productsmanagement.service;

import com.productsmanagement.productsmanagement.dto.ProductDTO;
import com.productsmanagement.productsmanagement.entity.ProductEntity;
import com.productsmanagement.productsmanagement.repository.ProductRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity product = convertToEntity(productDTO);
        return convertToDTO(productRepository.save(product));
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        if (!productRepository.existsById(id)) {
            return null;
        }
        ProductEntity product = convertToEntity(productDTO);
        product.setId(id);
        return convertToDTO(productRepository.save(product));
    }

    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

    public List<ProductDTO> findProducts(String name, String category, Double minPrice, Double maxPrice) {
        return productRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(name)) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (StringUtils.hasText(category)) {
                predicates.add(criteriaBuilder.equal(root.get("category"), category));
            }
            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(ProductEntity product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory()
        );
    }

    private ProductEntity convertToEntity(ProductDTO productDTO) {
        return new ProductEntity(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getQuantity(),
                productDTO.getCategory()
        );
    }
}

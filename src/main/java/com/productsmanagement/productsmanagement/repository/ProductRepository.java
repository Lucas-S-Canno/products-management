package com.productsmanagement.productsmanagement.repository;

import com.productsmanagement.productsmanagement.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDTO, Long> {
}

package com.Softito.ecommerce.dao;

import com.Softito.ecommerce.model.Product;
import com.Softito.ecommerce.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductDAO extends ListCrudRepository<Product, Long> {
    Optional<Product> findById(Long id);
    List<Product> findByDescription(String keyword);
}

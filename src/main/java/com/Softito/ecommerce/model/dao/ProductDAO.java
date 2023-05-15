package com.Softito.ecommerce.model.dao;

import com.Softito.ecommerce.model.Product;
import com.Softito.ecommerce.repository.ListCrudRepository;

public interface ProductDAO extends ListCrudRepository<Product, Long> {
}

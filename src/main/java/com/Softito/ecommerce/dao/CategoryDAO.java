package com.Softito.ecommerce.dao;

import com.Softito.ecommerce.model.Category;
import com.Softito.ecommerce.repository.ListCrudRepository;

import java.util.List;

public interface CategoryDAO extends ListCrudRepository<Category,Long> {

    List<Category> findByName(String name);

}

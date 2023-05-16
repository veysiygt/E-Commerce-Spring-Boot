package com.Softito.ecommerce.service;

import com.Softito.ecommerce.model.Category;
import com.Softito.ecommerce.dao.CategoryDAO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;

    public List<Category> getCategoriesByProductName(String productName) {
        List<Category> categories = categoryDAO.findByName(productName);
        return categories;
    }


}

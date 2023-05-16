package com.Softito.ecommerce.service;

import com.Softito.ecommerce.model.Product;
import com.Softito.ecommerce.model.dao.ProductDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

    public List<Product> getProducts(){
        return productDAO.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productDAO.findById(id);
    }

    public Product save(Product product) {
        try {
            return productDAO.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving product");
        }
    }

    public List<Product> searchProducts(String keyword) {
        List<Product> products = productDAO.findByDescription(keyword);
        return products;
    }

}

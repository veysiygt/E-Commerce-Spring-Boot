package com.Softito.ecommerce.service;

import com.Softito.ecommerce.model.Product;
import com.Softito.ecommerce.model.dao.ProductDAO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

    public List<Product> getProducts(){
        return productDAO.findAll();
    }

}

package com.Softito.ecommerce.api.controller;

import com.Softito.ecommerce.model.Category;
import com.Softito.ecommerce.model.Product;
import com.Softito.ecommerce.service.CategoryService;
import com.Softito.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Data
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/{id}/buy")
    public ResponseEntity<Product> buyProduct(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent() && product.get().getQuantity() > 0) {
            Product updatedProduct = product.get();
            updatedProduct.setQuantity(updatedProduct.getQuantity() - 1);
            try {
                productService.save(updatedProduct);
                return ResponseEntity.ok(updatedProduct);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{productName}/categories")
    public ResponseEntity<List<Category>> getCategoriesByProductName(@PathVariable String productName) {
        List<Category> categories = categoryService.getCategoriesByProductName(productName);
        if (categories != null) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}

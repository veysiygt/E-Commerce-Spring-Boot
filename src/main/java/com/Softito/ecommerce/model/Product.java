package com.Softito.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Long id;

    @NotBlank
    @Size(min = 3, message = "Product name must contain atleast 3 characters")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    //private String image;

    @NotBlank
    @Size(min = 6, message = "Product description must contain atleast 6 characters")
    @Column(name = "description", nullable = false)
    private String description;

    private Integer quantity;
    @Column(name = "price")
    private double price;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;



}
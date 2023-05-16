package com.Softito.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(min = 5, max = 512, message = "Adrress Line 1 must contain atleast 10 characters")
    @Column(name = "address_line_1", nullable = false, length = 512)
    private String addressLine1;

    @NotBlank
    @Size(min = 5, max = 512, message = "Address Line 2 must contain atleast 10 characters")
    @Column(name = "addres_line_2", nullable = false, length = 512)
    private String addressLine2;

    @NotBlank
    @Size(min = 3, max = 75, message = "Country name must contain atleast 3 characters")
    @Column(name = "country", nullable = false, length = 75)
    private String country;

    @NotBlank
    @Size(min = 3, message = "City name must contain atleast 3 characters")
    @Column(name = "city", nullable = false)
    private String city;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
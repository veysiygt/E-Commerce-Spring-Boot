package com.Softito.ecommerce.model.dao;

import com.Softito.ecommerce.model.User;
import com.Softito.ecommerce.repository.ListCrudRepository;

import java.util.Optional;

public interface UserDAO extends ListCrudRepository<User,Long> {
    //ListCrudRepo arayüzünü uygula veritabanı işlemlerini yap
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}

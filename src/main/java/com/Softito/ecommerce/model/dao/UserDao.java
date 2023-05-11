package com.Softito.ecommerce.model.dao;

import com.Softito.ecommerce.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User,Long> {
    //CrudRepo arayüzünü uygula veritabanı işlemlerini yap
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}

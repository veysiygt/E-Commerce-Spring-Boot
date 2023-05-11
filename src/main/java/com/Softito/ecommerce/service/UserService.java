package com.Softito.ecommerce.service;

import com.Softito.ecommerce.api.dto.RegisterDTO;
import com.Softito.ecommerce.exception.RegisterException;
import com.Softito.ecommerce.model.User;
import com.Softito.ecommerce.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    //Gelen Kullanıcıyı veritabanına kaydet username ve mail kontrolü
    public User registerUser(RegisterDTO registerDTO) throws RegisterException {
        if (userDao.findByEmail(registerDTO.getEmail()).isPresent()
                || userDao.findByUsername(registerDTO.getUsername()).isPresent()){
            throw new RegisterException();
        }
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setUsername(registerDTO.getUsername());
        //Şifreyi şifrele
        user.setPassword(registerDTO.getPassword());
        return userDao.save(user);
    }

}

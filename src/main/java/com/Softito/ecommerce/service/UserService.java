package com.Softito.ecommerce.service;

import com.Softito.ecommerce.api.dto.LoginRequestDTO;
import com.Softito.ecommerce.api.dto.RegisterDTO;
import com.Softito.ecommerce.exception.RegisterException;
import com.Softito.ecommerce.model.User;
import com.Softito.ecommerce.model.dao.UserDAO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserDAO userDao;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private EncryptionService encryptionService;//şifre şifrele

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
        user.setPassword(encryptionService.encryptPassword(registerDTO.getPassword()));
        return userDao.save(user);
    }

    public String loginUser(LoginRequestDTO loginRequestDTO){
        Optional<User> opUser = userDao.findByUsername(loginRequestDTO.getUsername());
        if (opUser.isPresent()){
            User user = opUser.get();
            if (encryptionService.verifyPassword(loginRequestDTO.getPassword(), user.getPassword())){
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }

}

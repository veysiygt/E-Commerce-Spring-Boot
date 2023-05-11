package com.Softito.ecommerce.restController;

import com.Softito.ecommerce.model.Admin;
import com.Softito.ecommerce.repository.AdminRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/api")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") Long id) {
        Optional<Admin> adminData = adminRepository.findById(id);
        if (adminData.isPresent()) {
            return new ResponseEntity<>(adminData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admins")
    public ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin) {
        Admin newAdmin = adminRepository.save(admin);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/admins/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") Long id, @RequestBody Admin admin) {
        Optional<Admin> adminData = adminRepository.findById(id);
        if (adminData.isPresent()) {
            Admin updatedAdmin = adminData.get();
            updatedAdmin.setFirstName(admin.getFirstName());
            updatedAdmin.setLastName(admin.getLastName());
            updatedAdmin.setEmail(admin.getEmail());
            updatedAdmin.setPassword(admin.getPassword());
            //updatedAdmin.setRoles(admin.getRoles());

            adminRepository.save(updatedAdmin);
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable("id") Long id) {
        try {
            adminRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}



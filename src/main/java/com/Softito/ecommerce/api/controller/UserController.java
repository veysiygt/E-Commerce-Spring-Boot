package com.Softito.ecommerce.api.controller;

import com.Softito.ecommerce.model.Address;
import com.Softito.ecommerce.model.User;
import com.Softito.ecommerce.model.dao.AddressDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AddressDAO addressDAO;

    @GetMapping("/{userId}/address")
    public ResponseEntity<List<Address>> getAddress(
            @AuthenticationPrincipal User user, @PathVariable Long userId) {
        if (!userHasPermission(user, userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(addressDAO.findByUser_Id(userId));
    }


    @PutMapping("/{userId}/address")//if userhaspermission change address
    public ResponseEntity<Address> putAddress(
            @AuthenticationPrincipal User user, @PathVariable Long userId, @RequestBody Address address) {
        if (!userHasPermission(user, userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        address.setId(null);
        User refUser = new User();
        refUser.setId(userId);
        address.setUser(refUser);
        return ResponseEntity.ok(addressDAO.save(address));
    }

    @PatchMapping("/{userId}/address/{addressId}")//update address
    public ResponseEntity<Address> patchAddress(
            @AuthenticationPrincipal User user, @PathVariable Long userId,
            @PathVariable Long addressId, @RequestBody Address address) {
        if (!userHasPermission(user, userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (address.getId() == addressId) {
            Optional<Address> opOriginalAddress = addressDAO.findById(addressId);
            if (opOriginalAddress.isPresent()) {
                User originalUser = opOriginalAddress.get().getUser();
                if (originalUser.getId() == userId) {
                    address.setUser(originalUser);
                    return ResponseEntity.ok(addressDAO.save(address));
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }

    private boolean userHasPermission(User user, Long id){
        return user.getId() == id;
    }

}

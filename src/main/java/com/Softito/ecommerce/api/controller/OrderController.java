package com.Softito.ecommerce.api.controller;

import com.Softito.ecommerce.model.Order;
import com.Softito.ecommerce.model.User;
import com.Softito.ecommerce.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping
    public List<Order> getOrders(@AuthenticationPrincipal User user){
        return orderService.getOrders(user);
    }

}

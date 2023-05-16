package com.Softito.ecommerce.service;

import com.Softito.ecommerce.model.Order;
import com.Softito.ecommerce.model.User;
import com.Softito.ecommerce.dao.OrderDAO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;
    public List<Order> getOrders(User user){
        return orderDAO.findByUser(user);
    }


}

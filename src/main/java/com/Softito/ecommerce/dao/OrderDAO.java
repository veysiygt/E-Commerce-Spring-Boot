package com.Softito.ecommerce.dao;

import com.Softito.ecommerce.model.Order;
import com.Softito.ecommerce.model.User;
import com.Softito.ecommerce.repository.ListCrudRepository;

import java.util.List;

public interface OrderDAO extends ListCrudRepository<Order, Long> {

    List<Order> findByUser(User user);

}

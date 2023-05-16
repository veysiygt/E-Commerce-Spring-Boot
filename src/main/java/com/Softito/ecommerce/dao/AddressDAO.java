package com.Softito.ecommerce.dao;

import com.Softito.ecommerce.model.Address;
import com.Softito.ecommerce.repository.ListCrudRepository;

import java.util.List;

public interface AddressDAO extends ListCrudRepository<Address, Long> {

    List<Address> findByUser_Id(Long id);

}

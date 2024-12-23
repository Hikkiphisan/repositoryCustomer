package org.example.customerrepositorymanagement.service;

import org.example.customerrepositorymanagement.model.Customer;
import org.example.customerrepositorymanagement.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends IGenerateService<Customer>{
    Iterable<Customer> findAllByProvince(Province province);

    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findAllByFirstNameContaining(Pageable pageable, String name);
}

package org.example.customerrepositorymanagement.repository;


import org.example.customerrepositorymanagement.model.Customer;
import org.example.customerrepositorymanagement.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Long> {
    Iterable<Customer> findAllByProvince(Province province);
    Page<Customer> findAllByFirstNameContaining(Pageable pageable, String name);
    Page<Customer> findAll(Pageable pageable);

}
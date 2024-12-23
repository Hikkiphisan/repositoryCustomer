package org.example.customerrepositorymanagement.service.impl;
import org.example.customerrepositorymanagement.model.Customer;
import org.example.customerrepositorymanagement.model.Province;
import org.example.customerrepositorymanagement.repository.ICustomerRepository;
import org.example.customerrepositorymanagement.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service  //Service chịu trách nhiệm chính trong việc xử lý nghiệp vụ (business logic), còn Repository chỉ thực hiện việc giao tiếp trực tiếp với cơ sở dữ liệu.
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return iCustomerRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iCustomerRepository.deleteById(id);
    }

    @Override
    public Iterable<Customer> findAllByProvince(Province province) {
        return iCustomerRepository.findAllByProvince(province);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return iCustomerRepository.findAll(pageable);
    }


    @Override
    public Page<Customer> findAllByFirstNameContaining(Pageable pageable, String name) {
        return iCustomerRepository.findAllByFirstNameContaining(pageable, name);
    }
}
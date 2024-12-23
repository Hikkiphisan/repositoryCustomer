package org.example.customerrepositorymanagement.repository;

import org.example.customerrepositorymanagement.model.Province;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends CrudRepository<Province, Long> {
}
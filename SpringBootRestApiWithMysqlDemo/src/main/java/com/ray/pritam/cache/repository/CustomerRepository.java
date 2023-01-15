package com.ray.pritam.cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ray.pritam.cache.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}

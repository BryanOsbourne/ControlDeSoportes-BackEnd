package com.cs.cn.repository;

import com.cs.cn.model.Customer;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCodigo(Long codigo);
    ArrayList<Customer> findByStateTrue();
}

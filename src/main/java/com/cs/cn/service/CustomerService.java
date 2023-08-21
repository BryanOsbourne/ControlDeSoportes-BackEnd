package com.cs.cn.service;

import com.cs.cn.model.Customer;
import java.util.ArrayList;
import java.util.Optional;

public interface CustomerService {
    public Customer save(Customer customer);
    public Optional<Customer> findById(Long id);
    public ArrayList<Customer> findAll();
    public Optional<Customer> findByCodigo(Long codigo);
    public ArrayList<Customer> findActives();
}

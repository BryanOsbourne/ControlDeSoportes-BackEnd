package com.cs.cn.service;

import com.cs.cn.model.Customer;
import com.cs.cn.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplCustomerService implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
    @Override
    public ArrayList<Customer> findAll() {
        return (ArrayList<Customer>) customerRepository.findAll();
    }
    @Override
    public Optional<Customer> findByCodigo(Long codigo) {
        return customerRepository.findByCodigo(codigo);
    }
    @Override
    public ArrayList<Customer> findActives() {
        return customerRepository.findByStateTrue();
    }

}

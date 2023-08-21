package com.cs.cn.controller;

import com.cs.cn.constans.ApplicationConstants;
import com.cs.cn.constans.ServerConstants;
import com.cs.cn.model.Customer;
import com.cs.cn.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = ServerConstants.CLIENT_FRONTEND)
@RequestMapping(ApplicationConstants.APPLICATION_TICKET_TRACE + ApplicationConstants.RESOURCE_CUSTOMER)
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    
    @GetMapping("/findByCodigo")
    public ResponseEntity<Customer> findById(@RequestParam("codigo") Long codigo) {
        try {
            return new ResponseEntity<>(customerService.findByCodigo(codigo).orElse(null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<ArrayList<Customer>> findAll() {
        try {
            return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/findActives")
    public ResponseEntity<ArrayList<Customer>> findActives() {
        try {
            return new ResponseEntity<>(customerService.findActives(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/save")
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

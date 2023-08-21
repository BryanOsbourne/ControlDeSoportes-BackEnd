package com.cs.cn.repository;

import com.cs.cn.model.Customer;
import com.cs.cn.model.Support;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepository extends JpaRepository<Support, Long> {
    ArrayList<Support> findByCustomer(Customer customer);

}

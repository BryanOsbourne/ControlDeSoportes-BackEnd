package com.cs.cn.service;

import com.cs.cn.model.Support;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SupportService {
    public Support save(Support support);
    public Optional<Support> findById(Long id);
    public ArrayList<Support> findAll();
    public ArrayList<Support> findByCustomer(Long customerId);
    public List<Support> findByCriterias(
            Long agentId, Long customerId, String state, String supporType, LocalDate startDate, LocalDate endDate);
}

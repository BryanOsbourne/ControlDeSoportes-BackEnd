package com.cs.cn.service.implementation;

import com.cs.cn.constans.EntityAgentConstants;
import com.cs.cn.constans.EntityCustomerConstans;
import com.cs.cn.constans.EntitySupportConstans;
import com.cs.cn.model.Agent;
import com.cs.cn.model.Customer;
import com.cs.cn.model.Support;
import com.cs.cn.model.LogSupport;
import com.cs.cn.repository.CustomerRepository;
import java.util.*;
import com.cs.cn.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.transaction.annotation.Transactional;
import com.cs.cn.repository.SupportRepository;
import com.cs.cn.repository.LogSupportRepository;

@Service
public class ImplSupportService implements SupportService {
    @Autowired
    private SupportRepository supportRepository;
    @Autowired
    private LogSupportRepository logSupportRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Support save(Support support) {
        LogSupport logSupport = createLogBySupport(support);
        logSupportRepository.save(logSupport);
        return supportRepository.save(support);
    }

    @Override
    public Optional<Support> findById(Long id) {
        return supportRepository.findById(id);
    }

    @Override
    public ArrayList<Support> findAll() {
        return (ArrayList<Support>) supportRepository.findAll();
    }

    @Override
    @Transactional
    public ArrayList<Support> findByCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return supportRepository.findByCustomer(customer);
    }

    private LogSupport createLogBySupport(Support support) {
        LogSupport logSupport = new LogSupport();
        logSupport.setStartDate(support.getStartDate());
        logSupport.setStartTime(support.getStartTime());
        logSupport.setAgent(support.getAgent());
        logSupport.setCustomer(support.getCustomer());
        logSupport.setSupportType(support.getSupportType());
        logSupport.setContact(support.getContact());
        logSupport.setPhone(support.getPhone());
        logSupport.setDetail(support.getDetail());
        logSupport.setObservation(support.getObservation());
        logSupport.setEndDate(support.getEndDate());
        logSupport.setEndTime(support.getEndTime());
        logSupport.setState(support.getState());
        logSupport.setSupport(support);
        return logSupport;
    }

    @Override
    @Transactional
    public List<Support> findByCriterias(
            Long agentId, Long customerId, String state, String supportType, Date startDate, Date endDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Support> query = criteriaBuilder.createQuery(Support.class);
        Root<Support> rootSupport = query.from(Support.class);
        List<Predicate> predicates = new ArrayList<>();

       predicates.add(criteriaBuilder.between(rootSupport.get(EntitySupportConstans.COL_NAME_START_DATE), startDate, endDate));

        if (agentId != 0) {
            Join<Support, Agent> jointAgent = rootSupport.join(EntityAgentConstants.TAB_NAME_AGENT);
            predicates.add(criteriaBuilder.equal(jointAgent.get(EntityAgentConstants.COL_NAME_ID), agentId));
        }

        if (customerId != 0) {
            Join<Support, Customer> joinCustomer = rootSupport.join(EntityCustomerConstans.TAB_NAME_CUSTOMER);
            predicates.add(criteriaBuilder.equal(joinCustomer.get(EntityCustomerConstans.COL_NAME_CODIGO), customerId));
        }

        if (!state.equals("")) {
            predicates.add(criteriaBuilder.equal(rootSupport.get(EntitySupportConstans.COL_NAME_STATE), state));
        }

        if (!supportType.equals("")) {
            predicates.add(criteriaBuilder.equal(rootSupport.get(EntitySupportConstans.COL_NAME_SUPPORT_TYPE), supportType));
        }
        query.where(predicates.toArray(new Predicate[0]));
        List<Support> supports = entityManager.createQuery(query).getResultList();
        return supports;
    }

}

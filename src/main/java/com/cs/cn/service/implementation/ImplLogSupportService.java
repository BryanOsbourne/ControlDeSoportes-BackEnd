package com.cs.cn.service.implementation;

import com.cs.cn.constans.EntityAgentConstants;
import com.cs.cn.constans.EntitySupportConstans;
import com.cs.cn.model.Agent;
import com.cs.cn.model.Support;
import com.cs.cn.model.LogSupport;
import java.util.ArrayList;
import java.util.Optional;

import com.cs.cn.service.LogSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cs.cn.repository.SupportRepository;
import com.cs.cn.repository.LogSupportRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Service
public class ImplLogSupportService implements LogSupportService {

    @Autowired
    private LogSupportRepository logSupportRepository;
    @Autowired
    private SupportRepository supportRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public LogSupport save(LogSupport logSupport) {
        return logSupportRepository.save(logSupport);
    }
    @Override
    public Optional<LogSupport> findById(Long id) {
        return logSupportRepository.findById(id);
    }
    @Override
    @Transactional
    public ArrayList<LogSupport> findAllBySupport(Long supportId) {
        Support support = supportRepository.findById(supportId).orElse(null);
        return logSupportRepository.findBySupport(support);
    }
    @Override
    public List<LogSupport> findByCriterias(Long agentId, Long supportId, Date startDate, Date endDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<LogSupport> query = criteriaBuilder.createQuery(LogSupport.class);
        Root<LogSupport> rootLogSupport = query.from(LogSupport.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.between(rootLogSupport.get(EntitySupportConstans.COL_NAME_START_DATE), startDate, endDate));

        if (agentId != 0) {
            Join<LogSupport, Agent> asesorJoin = rootLogSupport.join(EntityAgentConstants.TAB_NAME_AGENT);
            predicates.add(criteriaBuilder.equal(asesorJoin.get(EntityAgentConstants.COL_NAME_ID), agentId));
        }

        if (supportId != 0) {
            Join<LogSupport, Support> joinSupport = rootLogSupport.join(EntitySupportConstans.TAB_NAME_SUPPORT);
            predicates.add(criteriaBuilder.equal(joinSupport.get(EntitySupportConstans.COL_NAME_ID), supportId));
        }

        query.where(predicates.toArray(new Predicate[0]));
        List<LogSupport> logSupports = entityManager.createQuery(query).getResultList();
        return logSupports;
    }

}

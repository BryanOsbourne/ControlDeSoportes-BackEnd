package com.cs.cn.service;

import com.cs.cn.model.Agent;
import com.cs.cn.repository.AgentRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ImplAgentService implements AgentService {
    @Autowired
    private AgentRepository agentRepository;
    @Override
    public Optional<Agent> findById(Long id) {
        return agentRepository.findById(id);
    }
    @Override
    public ArrayList<Agent> findAll() {
        return (ArrayList<Agent>) agentRepository.findAll();
    }
    @Override
    public ArrayList<Agent> findActives() {
        return agentRepository.findByStateTrue();
    }
    @Override
    public Agent findByUsername(String username) {
        return agentRepository.findByUsername(username).orElse(null);
    }
}

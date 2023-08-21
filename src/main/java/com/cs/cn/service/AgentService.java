package com.cs.cn.service;

import com.cs.cn.model.Agent;
import java.util.ArrayList;
import java.util.Optional;

public interface AgentService {
    public Optional<Agent> findById(Long id);
    public ArrayList<Agent> findAll();
    public ArrayList<Agent> findActives();
    public Agent findByUsername(String username);
}

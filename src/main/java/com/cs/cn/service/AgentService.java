package com.cs.cn.service;

import com.cs.cn.model.Agent;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public interface AgentService {
    public Optional<Agent> findById(Long id);
    public ArrayList<Agent> findAll();
    public ArrayList<Agent> findActives();
    public Agent findByUsername(String username);
    public Map<String, String> uploadAgentPhoto(MultipartFile file, Long id);
    public Resource getAgentPhoto(String fileName);
}

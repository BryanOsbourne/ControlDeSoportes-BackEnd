package com.cs.cn.service.implementation;

import com.cs.cn.helper.FileUtility;
import com.cs.cn.model.Agent;
import com.cs.cn.repository.AgentRepository;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import com.cs.cn.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImplAgentService implements AgentService {
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private FileUtility fileUtility;

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
    @Override
    public Map<String, String> uploadAgentPhoto(MultipartFile file, Long id) {
        Agent agent = agentRepository.findById(id).orElse(null);
        String urlPhoto = fileUtility.uploadPhotoAgent(file);
        if(agent != null){
            agent.setPhoto(urlPhoto);
            agentRepository.save(agent);
        }
        return Map.of("url", urlPhoto);
    }
    @Override
    public Resource getAgentPhoto(String fileName) {
        return fileUtility.getAgentPhoto(fileName);
    }
}

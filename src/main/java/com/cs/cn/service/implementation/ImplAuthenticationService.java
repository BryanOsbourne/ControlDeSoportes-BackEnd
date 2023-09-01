package com.cs.cn.service.implementation;

import com.cs.cn.model.Agent;
import com.cs.cn.security.AuthenticationResponse;
import com.cs.cn.repository.AgentRepository;
import com.cs.cn.security.AuthenticationRequest;
import com.cs.cn.security.JwtService;
import com.cs.cn.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImplAuthenticationService implements AuthenticationService {

    private final AgentRepository agentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public Agent create(Agent request) {
        if (request.getId() != null) {
            Agent agent = agentRepository.findById(request.getId()).orElse(null);
            String password = agent.getPassword();
            agent = request;
            agent.setPassword(password);
            return agentRepository.save(agent);
        } else {
            request.setPhoto("http://localhost:8080/v1/app-ticket-trace/agents/userPhoto/logo.png");
            request.setPassword(passwordEncoder.encode("12345"));
            return agentRepository.save(request);
        }
    }
    @Override
    @Transactional
    public Agent update(Agent request) {
        Agent agent = agentRepository.findById(request.getId()).orElse(null);
        if (agent != null) {
            if (request.getPassword() == null) {
                String password = agent.getPassword();
                agent = request;
                agent.setPassword(password);
                return agentRepository.save(agent);
            } else {
                agent = request;
                agent.setPassword(passwordEncoder.encode(request.getPassword()));
                return agentRepository.save(agent);
            }
        }
        return agent;
    }
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        Agent agent = agentRepository.findByUsername(request.getUsername()).orElse(null);
        if (!agent.getState()) {
            return null;
        }
        String token = jwtService.generateToken(agent);
        return AuthenticationResponse.builder()
                .token(token)
                .agent(agent)
                .build();
    }
    @Override
    public Boolean recover(String username) {
        Agent agent = agentRepository.findByUsername(username).orElse(null);
        if (agent != null) {
            agent.setPassword(passwordEncoder.encode("12345"));
            agent.setState(false);
            agentRepository.save(agent);
            return true;
        }
        return false;
    }
}

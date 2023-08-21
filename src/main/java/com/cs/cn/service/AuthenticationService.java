package com.cs.cn.service;

import com.cs.cn.model.Agent;
import com.cs.cn.security.AuthenticationRequest;
import com.cs.cn.security.AuthenticationResponse;

public interface AuthenticationService {
    public Agent create(Agent agent);
    public AuthenticationResponse authenticate(AuthenticationRequest request);
    public Agent update(Agent request);
    public Boolean recover(String username);
}

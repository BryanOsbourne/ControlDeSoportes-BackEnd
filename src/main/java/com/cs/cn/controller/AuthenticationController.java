package com.cs.cn.controller;

import com.cs.cn.constans.ApplicationConstants;
import com.cs.cn.constans.ServerConstants;
import com.cs.cn.model.Agent;
import com.cs.cn.security.AuthenticationResponse;
import com.cs.cn.security.AuthenticationRequest;
import com.cs.cn.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = ServerConstants.CLIENT_FRONTEND)
@RequestMapping(ApplicationConstants.APPLICATION_TICKET_TRACE + ApplicationConstants.RESOURCE_AUTHENTICATION)
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Agent> create(@RequestBody Agent agent) {
        try {
            return new ResponseEntity<>(authenticationService.create(agent), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Agent> update(@RequestBody Agent request) {
        try {
            return new ResponseEntity<>(authenticationService.update(request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/recover")
    public ResponseEntity<Boolean> recover(@RequestBody String username) {
        try {
            return new ResponseEntity<>(authenticationService.recover(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

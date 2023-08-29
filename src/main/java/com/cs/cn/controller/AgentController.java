package com.cs.cn.controller;

import com.cs.cn.constans.ApplicationConstants;
import com.cs.cn.constans.ServerConstants;
import com.cs.cn.model.Agent;
import com.cs.cn.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin(origins = ServerConstants.CLIENT_FRONTEND)
@RequestMapping(ApplicationConstants.APPLICATION_TICKET_TRACE + ApplicationConstants.RESOURCE_AGENT)
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping("/findById")
    public ResponseEntity<Agent> findById(@RequestParam("id") Long id) {
        try {
            return new ResponseEntity<>(agentService.findById(id).orElse(null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findActives")
    public ResponseEntity<ArrayList<Agent>> findActives() {
        try {
            return new ResponseEntity<>(agentService.findActives(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<ArrayList<Agent>> findAll() {
        try {
            return new ResponseEntity<>(agentService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/upload")
    public Map<String, String> uploadPhoto(
            @RequestParam("file") MultipartFile file,
            @RequestParam("agentId") Long id) {
        return agentService.uploadAgentPhoto(file,id);
    }

    @GetMapping("/userPhoto/{fileName:.+}")
    public ResponseEntity<Resource> getUserPhoto(@PathVariable String fileName) {
        try {
            Resource file = agentService.getAgentPhoto(fileName);
            String contentType = Files.probeContentType(file.getFile().toPath());
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .body(file);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

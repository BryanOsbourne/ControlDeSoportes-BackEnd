package com.cs.cn.controller;

import com.cs.cn.constans.ApplicationConstants;
import com.cs.cn.constans.ServerConstants;
import com.cs.cn.model.LogSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cs.cn.service.LogSupportService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@CrossOrigin(origins = ServerConstants.CLIENT_FRONTEND)
@RequestMapping(ApplicationConstants.APPLICATION_TICKET_TRACE + ApplicationConstants.RESOURCE_LOG_SUPPORTS)
public class LogSupportController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(LogSupportController.class);
    
    @Autowired
    private LogSupportService logSupportService;

    @GetMapping("/findById")
    public ResponseEntity<LogSupport> findById(@RequestParam("id") Long id) {
        try {
            return new ResponseEntity<>(logSupportService.findById(id).orElse(null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findAllBySupportId")
    public ResponseEntity<ArrayList<LogSupport>> findAllBy(@RequestParam("supportId") Long supportId) {
        try {
            return new ResponseEntity<>(logSupportService.findAllBySupport(supportId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByCriterias")
    public ResponseEntity<List<LogSupport>> findByCriterias(
            @RequestParam("agentId") Long agentId,
            @RequestParam("supportId") Long supportId,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDate endDate) {
        try {
            return new ResponseEntity<>(logSupportService.findByCriterias(
                    agentId, supportId, startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

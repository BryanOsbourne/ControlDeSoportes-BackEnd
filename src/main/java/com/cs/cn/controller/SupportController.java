package com.cs.cn.controller;

import com.cs.cn.constans.ApplicationConstants;
import com.cs.cn.constans.ServerConstants;
import com.cs.cn.model.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cs.cn.service.SupportService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.*;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@CrossOrigin(origins = ServerConstants.CLIENT_FRONTEND)
@RequestMapping(ApplicationConstants.APPLICATION_TICKET_TRACE + ApplicationConstants.RESOURCE_SUPPORTS)
public class SupportController {

    private final Logger LOGGER = LoggerFactory.getLogger(SupportController.class);

    @Autowired
    private SupportService supportService;

    @PostMapping("/save")
    public ResponseEntity<Support> save(@RequestBody Support support) {
        LOGGER.info("---> {}",support);
        try {
            return new ResponseEntity(supportService.save(support), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam("id") Long id) {
        try {
            return new ResponseEntity<>(supportService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping("/findAllByCustomerId")
    public ResponseEntity<ArrayList<Support>> findAllByCustomer(@RequestParam("customerId") Long customerId) {
        try {
            return new ResponseEntity<>(supportService.findByCustomer(customerId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<ArrayList<Support>> findAll() {
        try {
            return new ResponseEntity<>(supportService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByCriterias")
    public ResponseEntity<List<Support>> findByCriterias(
            @RequestParam("agentId") Long agentId,
            @RequestParam("customerId") Long customerId,
            @RequestParam("state") String state,
            @RequestParam("supportType") String supportType,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
        try {
            return new ResponseEntity<>(supportService.findByCriterias(
                    agentId, customerId, state, supportType, startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

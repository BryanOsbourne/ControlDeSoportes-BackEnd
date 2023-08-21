package com.cs.cn.controller;

import com.cs.cn.constans.ApplicationConstants;
import com.cs.cn.constans.ServerConstants;
import com.cs.cn.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;

//https://www.youtube.com/watch?v=iiqaidp6RkI&ab_channel=ACADEMIAFULLSTACK
//https://github.com/ctucnoc/app-biblioteca/blob/main/src/main/java/com/pe/crce/biblioteca/constant/BibliotecaConstant.java

@RestController
@CrossOrigin(origins = ServerConstants.CLIENT_FRONTEND)
@RequestMapping(ApplicationConstants.APPLICATION_TICKET_TRACE + ApplicationConstants.RESOURCE_REPORTS)
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/export-supports")
    public ResponseEntity<Resource> generateReportBy(
            @RequestParam("agentId") Long agentId,
            @RequestParam("customerId") Long customerId,
            @RequestParam("state") String state,
            @RequestParam("supportType") String supportType,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate
    ) {

        try {

            File file = reportService.downloadSupports(agentId, customerId, state, supportType, startDate, endDate);

            //Configuracion de cabecera de las respuestas
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.parseMediaType(ApplicationConstants.EXCEL_MEDIA_TYPE));
            //  header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            header.setContentDispositionFormData("attachment", file.getName());

            //Crear respuesta HTTP con objeto file
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            return new ResponseEntity<>(fileSystemResource, header, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

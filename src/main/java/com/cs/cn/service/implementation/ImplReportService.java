package com.cs.cn.service.implementation;

import com.cs.cn.constans.ApplicationConstants;
import com.cs.cn.helper.ExcelExport;
import com.cs.cn.model.Support;
import com.cs.cn.service.ReportService;
import com.cs.cn.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class ImplReportService implements ReportService {
    @Autowired
    private SupportService supportService;

    @Override
    @Transactional
    public File downloadSupports(Long agentId, Long customerId, String state, String supportType, Date startDate, Date endDate) {

        List<Support> supports = supportService.findByCriterias(agentId, customerId, state, supportType, startDate, endDate);

        //NOMBRE DE LAS HOJAS
        String nameSheet = "Llamadas";
        List<String> sheets = List.of(nameSheet);

        //NOMBRE DE LAS COLUMNAS POR HOJAS
        Map<String, List<String>> colsBySheet = new HashMap<>();
        List<String> colsName = List.of("Fecha De Inicio", "Consecutivo", "Asesor", "Codigo Cliente", "Razon Social", "Contacto", "Telefono", "Tipo De Soporte", "Estado");

        //AGREGAR NOMBRE DE LAS HOJAS Y NOMBRE DE LAS COLUMNAS
        colsBySheet.put(nameSheet, colsName);

        //VALORES DE LAS HOJAS
        Map<String, List<Map<String, String>>> valuesBySheet = new HashMap<>();
        List<Map<String, String>> valoresHoja = new ArrayList<>();

        supports.forEach(row -> {
            Map<String, String> valoresDeHojasPorFila = new HashMap<>();
            valoresDeHojasPorFila.put("Fecha De Inicio", row.getStartDate().toString());
            valoresDeHojasPorFila.put("Consecutivo", row.getId().toString());
            valoresDeHojasPorFila.put("Asesor", row.getAgent().getFirstName());
            valoresDeHojasPorFila.put("Codigo Cliente", row.getCustomer().getCodigo().toString());
            valoresDeHojasPorFila.put("Razon Social", row.getCustomer().getBussinesName());
            valoresDeHojasPorFila.put("Contacto", row.getContact());
            valoresDeHojasPorFila.put("Telefono", row.getPhone());
            valoresDeHojasPorFila.put("Tipo De Soporte", row.getSupportType());
            valoresDeHojasPorFila.put("Estado", row.getState());
            valoresHoja.add(valoresDeHojasPorFila);
        });
        valuesBySheet.put(nameSheet, valoresHoja);
        try {
            Path temp = Files.createTempFile("Reporte De Llamadas", ApplicationConstants.DOCUMENT_FORMAT_EXCEL);
            return ExcelExport.generateExcel(sheets, colsBySheet, valuesBySheet, temp.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

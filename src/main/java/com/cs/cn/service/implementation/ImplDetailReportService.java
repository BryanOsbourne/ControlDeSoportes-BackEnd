package com.cs.cn.service.implementation;

import com.cs.cn.model.DetailReport;
import com.cs.cn.repository.DetailReportRepository;
import com.cs.cn.service.DetailReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ImplDetailReportService implements DetailReportService {

    @Autowired
    private DetailReportRepository detailReportRepository;

    @Override
    public Optional<DetailReport> findById(Long id) {
        return detailReportRepository.findById(id);
    }

    @Override
    public ArrayList<DetailReport> findAll() {
        return (ArrayList<DetailReport>) detailReportRepository.findAll();
    }

}

package com.cs.cn.service;

import com.cs.cn.model.DetailReport;
import java.util.ArrayList;
import java.util.Optional;

public interface DetailReportService {

    public Optional<DetailReport> findById(Long id);

    public ArrayList<DetailReport> findAll();

}

package com.cs.cn.service;

import com.cs.cn.model.BugReport;
import java.util.ArrayList;
import java.util.Optional;

public interface BugReportService {

    public Optional<BugReport> findById(Long id);

    public ArrayList<BugReport> findAll();

}

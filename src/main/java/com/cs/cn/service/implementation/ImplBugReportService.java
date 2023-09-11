package com.cs.cn.service.implementation;

import com.cs.cn.model.BugReport;
import com.cs.cn.repository.BugReportRepository;
import com.cs.cn.service.BugReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ImplBugReportService implements BugReportService {

    @Autowired
    private BugReportRepository bugReportRepository;

    @Override
    public Optional<BugReport> findById(Long id) {
        return bugReportRepository.findById(id);
    }

    @Override
    public ArrayList<BugReport> findAll() {
        return (ArrayList<BugReport>) bugReportRepository.findAll();
    }

}

package com.cs.cn.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public interface ReportService {
    public File downloadSupports(Long agentId, Long customerId, String state, String supportType, LocalDate startDate, LocalDate endDate) throws IOException;
}

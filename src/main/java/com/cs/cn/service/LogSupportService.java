package com.cs.cn.service;

import com.cs.cn.model.LogSupport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LogSupportService {
    public LogSupport save(LogSupport logSupport);
    public Optional<LogSupport> findById(Long id);
    public ArrayList<LogSupport> findAllBySupport(Long supportId);
    public List<LogSupport> findByCriterias(
            Long agent, Long supportId, LocalDate startDate, LocalDate endDate);
}

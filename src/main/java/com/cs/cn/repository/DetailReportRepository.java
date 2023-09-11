package com.cs.cn.repository;

import com.cs.cn.model.DetailReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailReportRepository  extends JpaRepository<DetailReport, Long> {

}

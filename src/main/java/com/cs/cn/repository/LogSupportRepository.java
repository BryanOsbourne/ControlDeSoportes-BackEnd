package com.cs.cn.repository;

import com.cs.cn.model.Support;
import com.cs.cn.model.LogSupport;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogSupportRepository extends JpaRepository<LogSupport, Long> {
    ArrayList<LogSupport> findBySupport(Support support);
}

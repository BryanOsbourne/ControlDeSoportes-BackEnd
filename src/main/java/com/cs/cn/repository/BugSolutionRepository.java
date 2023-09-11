package com.cs.cn.repository;

import com.cs.cn.model.BugSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugSolutionRepository  extends JpaRepository<BugSolution, Long> {

}

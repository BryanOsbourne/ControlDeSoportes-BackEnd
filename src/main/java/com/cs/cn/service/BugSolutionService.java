package com.cs.cn.service;

import com.cs.cn.model.BugSolution;
import java.util.ArrayList;
import java.util.Optional;

public interface BugSolutionService {

    public Optional<BugSolution> findById(Long id);

    public ArrayList<BugSolution> findAll();

}

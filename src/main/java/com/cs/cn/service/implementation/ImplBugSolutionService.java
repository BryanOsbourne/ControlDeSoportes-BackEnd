package com.cs.cn.service.implementation;

import com.cs.cn.model.BugSolution;
import com.cs.cn.repository.BugSolutionRepository;
import com.cs.cn.service.BugSolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ImplBugSolutionService implements BugSolutionService {

    @Autowired
    private BugSolutionRepository bugSolutionRepository;

    @Override
    public Optional<BugSolution> findById(Long id) {
        return bugSolutionRepository.findById(id);
    }

    @Override
    public ArrayList<BugSolution> findAll() {
        return (ArrayList<BugSolution>) bugSolutionRepository.findAll();
    }

}

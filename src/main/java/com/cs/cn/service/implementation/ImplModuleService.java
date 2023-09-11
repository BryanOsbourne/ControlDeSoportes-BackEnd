package com.cs.cn.service.implementation;

import com.cs.cn.model.Module;
import com.cs.cn.repository.ModuleRepository;
import com.cs.cn.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ImplModuleService implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public Optional<Module> findById(Long id) {
        return moduleRepository.findById(id);
    }

    @Override
    public ArrayList<Module> findAll() {
        return (ArrayList<Module>) moduleRepository.findAll();
    }

}

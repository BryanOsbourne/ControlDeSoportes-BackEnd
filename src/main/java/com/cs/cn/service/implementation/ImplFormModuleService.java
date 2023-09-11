package com.cs.cn.service.implementation;

import com.cs.cn.model.FormModule;
import com.cs.cn.repository.FormModuleRepository;
import com.cs.cn.service.FormModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ImplFormModuleService implements FormModuleService {

    @Autowired
    private FormModuleRepository formModuleRepository;

    @Override
    public Optional<FormModule> findById(Long id) {
        return formModuleRepository.findById(id);
    }

    @Override
    public ArrayList<FormModule> findAll() {
        return (ArrayList<FormModule>) formModuleRepository.findAll();
    }

}

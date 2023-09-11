package com.cs.cn.service;

import com.cs.cn.model.FormModule;
import java.util.ArrayList;
import java.util.Optional;

public interface FormModuleService {

    public Optional<FormModule> findById(Long id);

    public ArrayList<FormModule> findAll();

}

package com.cs.cn.service;

import java.util.ArrayList;
import java.util.Optional;
import com.cs.cn.model.Module;

public interface ModuleService {

    public Optional<Module> findById(Long id);

    public ArrayList<Module> findAll();

}

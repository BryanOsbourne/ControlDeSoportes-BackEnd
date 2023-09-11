package com.cs.cn.repository;

import com.cs.cn.model.FormModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormModuleRepository extends JpaRepository<FormModule, Long> {

}

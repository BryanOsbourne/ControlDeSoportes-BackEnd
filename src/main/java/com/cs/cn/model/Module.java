package com.cs.cn.model;

import com.cs.cn.constans.EntityModuleConstans;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = EntityModuleConstans.TAB_NAME_MODULE)
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EntityModuleConstans.COL_NAME_ID)
    private Long id;

    @Column(name = EntityModuleConstans.COL_NAME_NAME_MODULE, length = 40)
    private String nameModule;

    @OneToMany(mappedBy = EntityModuleConstans.TAB_NAME_MODULE, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FormModule> formModules;

}

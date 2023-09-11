package com.cs.cn.model;

import com.cs.cn.constans.EntityFormModuleConstans;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = EntityFormModuleConstans.TAB_NAME_FORM_MODULE)
public class FormModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EntityFormModuleConstans.COL_NAME_ID)
    private Long id;

    @Column(name = EntityFormModuleConstans.COL_NAME_NAME_FORM_MODULE, length = 40)
    private String nameFormModule;

    @ManyToOne
    @JoinColumn(name = EntityFormModuleConstans.COL_NAME_MODULE_ID)
    private Module module;

    @OneToMany(mappedBy = EntityFormModuleConstans.TAB_NAME_FORM_MODULE)
    private List<BugReport> bugReports;

}

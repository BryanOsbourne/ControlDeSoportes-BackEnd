package com.cs.cn.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "formModul")
public class FormModul implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nameFormModul")
    private String nameFormModul;

    @ManyToOne
    @JoinColumn(name = "modulId")
    private Modul modul;

    @OneToMany(mappedBy="formModul")
    private List<BugReport> bugReports;

}

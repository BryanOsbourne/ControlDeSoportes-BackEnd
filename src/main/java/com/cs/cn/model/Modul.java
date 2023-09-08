package com.cs.cn.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "modul")
public class Modul implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String nameModul;

    @OneToMany(mappedBy = "modul", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FormModul> formModuls;

}

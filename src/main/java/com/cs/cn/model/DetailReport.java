package com.cs.cn.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class DetailReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String platform;

    private String typeReport;

    private LocalDate version;

    private String summary;

    private String description;

    private String steps;

    private String actualResult;

    private String expectedResult;

    private String attachments;

    private String observation;

    @OneToOne(mappedBy = "detailReport")
    private BugReport bugReport;

}

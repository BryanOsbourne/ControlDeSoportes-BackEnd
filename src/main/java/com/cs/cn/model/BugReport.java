package com.cs.cn.model;

import com.cs.cn.constans.EntitySupportConstans;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bugReport")
public class BugReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private LocalDateTime dateReport;

    @ManyToOne()
    @JoinColumn(name = EntitySupportConstans.COL_NAME_AGENT_ID)
    private Agent agent;

    private String severity;

    private String priority;

    private Boolean status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "detailReportId")
    private DetailReport detailReport;

    @ManyToOne()
    @JoinColumn(name = "formModuleId")
    private FormModul formModul;

}

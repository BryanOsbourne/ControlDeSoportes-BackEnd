package com.cs.cn.model;

import com.cs.cn.constans.EntityBugReportConstants;
import com.cs.cn.constans.EntitySupportConstans;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = EntityBugReportConstants.TAB_NAME_BUG_REPORT)
public class BugReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EntityBugReportConstants.COL_NAME_ID)
    private Long id;

    @Column(name = EntityBugReportConstants.COL_NAME_DATE_REPORT, length = 20)
    private LocalDateTime dateReport;

    @ManyToOne
    @JoinColumn(name = EntitySupportConstans.COL_NAME_AGENT_ID)
    private Agent agent;

    @Column(name = EntityBugReportConstants.COL_NAME_SEVERITY, length = 20)
    private String severity;

    @Column(name = EntityBugReportConstants.COL_NAME_PRIORITY, length = 20)
    private String priority;

    @Column(name = EntityBugReportConstants.COL_NAME_STATUS, length = 1)
    private Boolean status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = EntityBugReportConstants.COL_NAME_DETAIL_REPORT_ID)
    private DetailReport detailReport;

    @ManyToOne
    @JoinColumn(name = EntityBugReportConstants.COL_NAME_FORM_MODULE_ID)
    private FormModule formModule;

    @OneToOne(mappedBy = EntityBugReportConstants.TAB_NAME_BUG_REPORT, cascade = CascadeType.ALL)
    private BugSolution bugSolution;

}

package com.cs.cn.model;

import com.cs.cn.constans.EntityDetailReportConstants;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = EntityDetailReportConstants.TAB_NAME_DETAIL_REPORT)
public class DetailReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EntityDetailReportConstants.COL_NAME_ID)
    private Long id;

    @Column(name = EntityDetailReportConstants.COL_NAME_PLATFORM, length = 20)
    private String platform;

    @Column(name = EntityDetailReportConstants.COL_NAME_TYPE_REPORT, length = 20)
    private String typeReport;

    @Column(name = EntityDetailReportConstants.COL_NAME_VERSION, length = 20)
    private LocalDate version;

    @Column(name = EntityDetailReportConstants.COL_NAME_SUMMARY, length = 50)
    private String summary;

    @Column(name = EntityDetailReportConstants.COL_NAME_DESCRIPTION, length = 254)
    private String description;

    @Column(name = EntityDetailReportConstants.COL_NAME_STEPS, length = 254)
    private String steps;

    @Column(name = EntityDetailReportConstants.COL_NAME_ACTUAL_RESULT, length = 254)
    private String actualResult;

    @Column(name = EntityDetailReportConstants.COL_NAME_EXPECTED_RESULT, length = 20)
    private String expectedResult;

    @Column(name = EntityDetailReportConstants.COL_NAME_ATTACHMENTS, length = 254)
    private String attachments;

    @Column(name = EntityDetailReportConstants.COL_NAME_OBSERVATION, length = 254)
    private String observation;

    @OneToOne(mappedBy = EntityDetailReportConstants.TAB_NAME_DETAIL_REPORT)
    private BugReport bugReport;

}

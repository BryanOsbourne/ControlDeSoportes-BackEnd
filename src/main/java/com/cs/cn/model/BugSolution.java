package com.cs.cn.model;

import com.cs.cn.constans.EntityBugSolutionConstants;
import com.cs.cn.constans.EntitySupportConstans;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = EntityBugSolutionConstants.TAB_NAME_BUG_SOLUTION)
public class BugSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EntityBugSolutionConstants.COL_NAME_ID)
    private Long id;

    @ManyToOne
    @JoinColumn(name = EntitySupportConstans.COL_NAME_AGENT_ID)
    private Agent agent;

    @Column(name = EntityBugSolutionConstants.COL_NAME_DATE_SOLUTION, length = 20)
    private LocalDateTime dateSolution;

    @Column(name = EntityBugSolutionConstants.COL_NAME_ATTACHMENTS)
    private String attachments;

    @Column(name = EntityBugSolutionConstants.COL_NAME_OBSERVATION, length = 254)
    private String observation;

    @OneToOne
    @JoinColumn(name = EntityBugSolutionConstants.COL_NAME_BUG_REPORT_ID)
    private BugReport bugReport;
    
}

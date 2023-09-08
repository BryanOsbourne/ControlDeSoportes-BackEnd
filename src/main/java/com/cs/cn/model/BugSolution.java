package com.cs.cn.model;

import com.cs.cn.constans.EntitySupportConstans;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "bugSolution")
public class BugSolution implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = EntitySupportConstans.COL_NAME_AGENT_ID)
    private Agent agent;

    private Date dateSolution;

    private String attachments;

    private String observation;

    @OneToOne()
    private BugReport bugReport;
    
}

package com.cs.cn.model;

import com.cs.cn.constans.EntitySupportConstans;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = EntitySupportConstans.TAB_NAME_SUPPORT)
public class Support implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EntitySupportConstans.COL_NAME_ID)
    private Long id;

    @Column(name = EntitySupportConstans.COL_NAME_START_DATE, length = 25)
    private LocalDate startDate;

    @Column(name = EntitySupportConstans.COL_NAME_START_TIME, length = 25)
    private String startTime;

    @ManyToOne()
    @JoinColumn(name = EntitySupportConstans.COL_NAME_AGENT_ID)
    private Agent agent;

    @ManyToOne()
    @JoinColumn(name = EntitySupportConstans.COL_NAME_CUSTOMER_ID)
    private Customer customer;

    @Column(name = EntitySupportConstans.COL_NAME_SUPPORT_TYPE, length = 50)
    private String supportType;

    @Column(name = EntitySupportConstans.COL_NAME_CONTACT, length = 50)
    private String contact;

    @Column(name = EntitySupportConstans.COL_NAME_PHONE, length = 50)
    private String phone;

    @Column(name = EntitySupportConstans.COL_NAME_DETAIL, length = 500)
    private String detail;

    @Column(name = EntitySupportConstans.COL_NAME_OBSERVATION, length = 500)
    private String observation;

    @Column(name = EntitySupportConstans.COL_NAME_END_DATE, length = 25)
    private LocalDate endDate;

    @Column(name = EntitySupportConstans.COL_NAME_END_TIME, length = 25)
    private String endTime;

    @Column(name = EntitySupportConstans.COL_NAME_STATE, length = 25)
    private String state;

    @JsonIgnore
    @OneToMany(mappedBy = EntitySupportConstans.TAB_NAME_SUPPORT, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LogSupport> logSupports;

}

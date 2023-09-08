package com.cs.cn.model;

import com.cs.cn.constans.EntitySupportConstans;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Entity
@Data
@Table(name = EntitySupportConstans.TAB_NAME_LOG_SUPPORT)
public class LogSupport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EntitySupportConstans.COL_NAME_ID)
    private Long id;

    @Column(name = EntitySupportConstans.COL_NAME_START_DATE, length = 25)
    private LocalDate startDate;

    @Column(name = EntitySupportConstans.COL_NAME_START_TIME, length = 25)
    private LocalTime startTime;

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
    private LocalTime endTime;

    @Column(name = EntitySupportConstans.COL_NAME_STATE, length = 25)
    private String state;

    @ManyToOne()
    @JoinColumn(name = EntitySupportConstans.COL_NAME_SUPPORT_ID)
    private Support support;

}

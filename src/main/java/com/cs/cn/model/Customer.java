package com.cs.cn.model;

import com.cs.cn.constans.EntityCustomerConstans;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = EntityCustomerConstans.TAB_NAME_CUSTOMER)
public class Customer extends Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EntityCustomerConstans.COL_NAME_ID)
    private Long id;

    @Column(name = EntityCustomerConstans.COL_NAME_CODIGO, unique = true)
    private Long codigo;

    @Column(name = EntityCustomerConstans.COL_NAME_BUSINESS_NAME, length = 50)
    private String bussinesName;

    @Column(name = EntityCustomerConstans.COL_NAME_VERSION, length = 25)
    private Date version;

    @JsonIgnore
    @OneToMany(mappedBy = EntityCustomerConstans.TAB_NAME_CUSTOMER, fetch = FetchType.LAZY)
    private List<Support> supports;

}

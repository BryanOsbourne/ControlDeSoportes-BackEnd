package com.cs.cn.model;

import com.cs.cn.constans.EntityPersonConstans;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person {

    @Column(name = EntityPersonConstans.COL_NAME_FIRST_NAME, length = 20)
    private String firstName;

    @Column(name = EntityPersonConstans.COL_NAME_SECOND_NAME, length = 20)
    private String secondName;

    @Column(name = EntityPersonConstans.COL_NAME_LAST_NAME, length = 20)
    private String lastName;

    @Column(name = EntityPersonConstans.COL_NAME_SECOND_SURNAME, length = 20)
    private String secondSurname;

    @Column(name = EntityPersonConstans.COL_NAME_ID_TYPE, length = 20)
    private String idType;

    @Column(name = EntityPersonConstans.COL_NAME_ID, length = 20)
    private String ccNit;

    @Column(name = EntityPersonConstans.COL_NAME_EMAIL, length = 100)
    private String email;

    @Column(name = EntityPersonConstans.COL_NAME_STATE, length = 1)
    private Boolean state;

}

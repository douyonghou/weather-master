package com.bs.weather.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author maotentai
 * @since 2020-03-06
 */
@Data
public class EmploymentInformation implements Serializable {

    private Integer informationId;

    private Integer studentNum;

    private String name;

    private ClassGrade classGrade;

    private Area area;

    private UnitKind unitKind;

    private String salary;

    private Provincial employmentWay;

    private String msg;

    private Date createTime;

    private College college;

    private Specialty specialty;

    private Integer gender;

}

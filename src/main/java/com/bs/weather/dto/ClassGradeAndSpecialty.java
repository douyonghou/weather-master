package com.bs.weather.dto;

import com.bs.weather.entity.ClassGrade;
import com.bs.weather.entity.PersonInfo;
import com.bs.weather.entity.Specialty;
import lombok.Data;

/**
 * @version 1.0
 */
@Data
public class ClassGradeAndSpecialty {
    private ClassGrade classGrade;
    private Specialty specialty;
    private PersonInfo personInfo;
    private Integer sum;
}

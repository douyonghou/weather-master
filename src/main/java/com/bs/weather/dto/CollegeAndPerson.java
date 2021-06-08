package com.bs.weather.dto;

import com.bs.weather.entity.College;
import com.bs.weather.entity.PersonInfo;
import lombok.Data;

/**
 * @version 1.0
 */
@Data
public class CollegeAndPerson {
    private College college;
    private PersonInfo personInfo;
    private Integer sum;
}

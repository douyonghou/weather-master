package com.bs.weather.dto;

import com.bs.weather.entity.College;
import com.bs.weather.entity.Specialty;
import lombok.Data;

/**
 * @version 1.0
 */
@Data
public class SpecialtyAndCollege {
    private Specialty specialty;
    private College college;
    private Integer sum;
}

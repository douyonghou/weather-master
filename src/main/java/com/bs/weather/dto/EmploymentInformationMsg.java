package com.bs.weather.dto;

import com.bs.weather.entity.EmploymentInformation;
import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 */
@Data
public class EmploymentInformationMsg {
    private Boolean success;
    private List<EmploymentInformation> list;
    private Integer count;
}

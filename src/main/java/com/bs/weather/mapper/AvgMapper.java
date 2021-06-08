package com.bs.weather.mapper;

import com.bs.weather.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface AvgMapper {

    @Select("select AVG(temperature) as temperature, AVG(aqi) as aqi, AVG(windpower) as windpower from AvgWeather where 1 = 1 ")
    List<AvgWeatherBean> queryavg();

}

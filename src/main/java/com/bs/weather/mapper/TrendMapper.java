package com.bs.weather.mapper;

import com.bs.weather.entity.AvgWeatherBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface TrendMapper {

    @Select("select dt, sum(temperature) as temperature from AvgWeather group by dt")
    List<AvgWeatherBean> querytrend();

    @Select("select dt, sum(sd) as sd from AvgWeather group by dt")
    List<AvgWeatherBean> querysd();

    @Select("select dt, sum(aqi) as aqi from AvgWeather group by dt")
    List<AvgWeatherBean> queryaqi();

    @Select("select dt, sum(windpower) as windpower from AvgWeather group by dt")
    List<AvgWeatherBean> querywindpower();





}

package com.bs.weather.service;

import com.bs.weather.entity.AvgWeatherBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maotentai
 * @since 2020-03-06
 */
public interface TrendService {

    List<AvgWeatherBean> getTrend();
    List<AvgWeatherBean> getsd();
    List<AvgWeatherBean> getaqi();
    List<AvgWeatherBean> getwindpower();
}

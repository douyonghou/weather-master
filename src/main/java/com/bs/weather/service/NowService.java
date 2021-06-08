package com.bs.weather.service;

import com.bs.weather.entity.Future15Day;
import com.bs.weather.entity.Future24Hour;
import com.bs.weather.entity.NowWeatherBean;
import com.bs.weather.entity.Provincial;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maotentai
 * @since 2020-03-06
 */
public interface NowService {

    List<Provincial> getProvincial();
    List<Provincial> getProvincial24hour();
    List<Provincial> getCity(String provincialId);
    List<NowWeatherBean> getArea(String getAreaId);
    List<NowWeatherBean> getAreaTop();
    List<NowWeatherBean> getAreaaqiTop();
    List<NowWeatherBean> getAreawindpowerTop();

    List<NowWeatherBean> getArearainTop();
    List<Provincial> getId(String id);
    List<NowWeatherBean> getNowWeather(String name);
    List<NowWeatherBean> getNowWeatherAll();
    List<Future24Hour> gethour24(String aeraId);
    List<Future15Day> getday15(String aeraId);
    List<Future15Day> getday40(String aeraId);





}

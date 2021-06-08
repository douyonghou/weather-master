package com.bs.weather.service.impl;

import com.bs.weather.entity.Future15Day;
import com.bs.weather.entity.Future24Hour;
import com.bs.weather.entity.NowWeatherBean;
import com.bs.weather.entity.Provincial;
import com.bs.weather.mapper.NowMapper;
import com.bs.weather.service.NowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author maotentai
 * @since 2020-03-06
 */
@Service
public class NowServiceImpl implements NowService {
    @Autowired
    NowMapper nowMapper;

    @Override
    public List<Provincial> getProvincial() { return nowMapper.queryProvincial(); }
    public List<Provincial> getProvincial24hour() { return nowMapper.queryProvincia24hourl(); }

    @Override
    public List<Provincial> getCity(String provincialId) { return nowMapper.queryCity(provincialId); }

    @Override
    public List<NowWeatherBean> getArea(String getAreaId){ return nowMapper.queryArea(getAreaId); }

    @Override
    public List<NowWeatherBean> getAreaTop(){ return nowMapper.queryAreaTop(); }

    @Override
    public List<NowWeatherBean> getAreaaqiTop() { return nowMapper.queryAreaAqiTop(); }

    @Override
    public List<NowWeatherBean> getArearainTop() { return nowMapper.queryAreaRainTop(); }


    @Override
    public List<NowWeatherBean> getAreawindpowerTop() { return nowMapper.queryAreaWindpowerTop(); }





    @Override
    public List<Provincial> getId(String id) { return nowMapper.queryId(id); }

    @Override
    public List<NowWeatherBean> getNowWeather(String name) { return nowMapper.queryNowWeather(name); }
    public List<NowWeatherBean> getNowWeatherAll() { return nowMapper.queryNowWeatherAll(); }

    @Override
    public List<Future24Hour> gethour24(String aeraId) { return nowMapper.queryFuture24Hour(aeraId); }

    @Override
    public List<Future15Day> getday15(String aeraId) { return nowMapper.queryFutureday15(aeraId); }
    public List<Future15Day> getday40(String aeraId) { return nowMapper.queryFutureday40(aeraId); }


}

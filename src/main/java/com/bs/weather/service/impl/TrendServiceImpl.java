package com.bs.weather.service.impl;

import com.bs.weather.entity.AvgWeatherBean;
import com.bs.weather.mapper.AvgMapper;
import com.bs.weather.mapper.TrendMapper;
import com.bs.weather.service.AvgService;
import com.bs.weather.service.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 */
@Service
public class TrendServiceImpl implements TrendService {
    @Autowired
    TrendMapper trendMapper;

    @Override
    public List<AvgWeatherBean> getTrend() { return trendMapper.querytrend(); }

    @Override
    public List<AvgWeatherBean> getsd() { return trendMapper.querysd(); }

    @Override
    public List<AvgWeatherBean> getaqi() { return trendMapper.queryaqi(); }

    @Override
    public List<AvgWeatherBean> getwindpower() { return trendMapper.querywindpower(); }


}

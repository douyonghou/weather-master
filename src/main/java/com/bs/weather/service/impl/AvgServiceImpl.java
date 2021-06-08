package com.bs.weather.service.impl;

import com.bs.weather.entity.*;
import com.bs.weather.mapper.AvgMapper;
import com.bs.weather.mapper.NowMapper;
import com.bs.weather.service.AvgService;
import com.bs.weather.service.NowService;
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
public class AvgServiceImpl implements AvgService {
    @Autowired
    AvgMapper avgMapper;

    @Override
    public List<AvgWeatherBean> getAvg() { return avgMapper.queryavg(); }
}

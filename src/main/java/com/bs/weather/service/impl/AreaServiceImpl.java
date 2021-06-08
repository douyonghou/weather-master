package com.bs.weather.service.impl;

import com.bs.weather.entity.Area;
import com.bs.weather.mapper.AreaMapper;
import com.bs.weather.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaMapper areaMapper;
    @Override
    public List<Area> getArea(Area area) {
        return areaMapper.queryArea(area);
    }
}

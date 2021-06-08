package com.bs.weather.service.impl;

import com.bs.weather.entity.UnitKind;
import com.bs.weather.mapper.UnitKindMapper;
import com.bs.weather.service.UnitKindService;
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
public class UnitKindServiceImpl implements UnitKindService {
    @Autowired
    UnitKindMapper unitKindMapper;

    @Override
    public List<UnitKind> getUnitKind() {
        return unitKindMapper.queryUnitKind();
    }
}

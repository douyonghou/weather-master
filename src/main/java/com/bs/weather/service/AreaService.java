package com.bs.weather.service;

import com.bs.weather.entity.Area;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maotentai
 * @since 2020-03-06
 */
public interface AreaService  {
    /**
     * 查询地区
     * @param area
     * @return
     */
    List<Area>getArea(Area area);
}

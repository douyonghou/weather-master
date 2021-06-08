package com.bs.weather.mapper;

import com.bs.weather.entity.Area;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author maotentai
 * @since 2020-03-06
 */
public interface AreaMapper {
    /**
     * 获取地区信息
     * @param area
     * @return
     */

    @Select("select area_id, area_name from maparea where area_id=#{area.areaId}")
    List<Area> queryArea(@Param("area") Area area);
}

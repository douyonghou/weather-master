package com.bs.weather.mapper;

import com.bs.weather.entity.UnitKind;
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
public interface UnitKindMapper {
    /**
     * @return
     */
    @Select("select * from tb_unit_kind")
    List<UnitKind> queryUnitKind();

}

package com.bs.weather.mapper;

import com.bs.weather.entity.Future15Day;
import com.bs.weather.entity.Future24Hour;
import com.bs.weather.entity.NowWeatherBean;
import com.bs.weather.entity.Provincial;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface NowMapper {
    /**
     * 获取省级别
     * @return
     */
    @Select("select DISTINCT id, name, pid from (select id, name, pid from City where pid = 0) L join NowWeather R on L.name = R.c7")
    List<Provincial> queryProvincia24hourl();

    @Select("select id, name, pid from City where id in(230000,140000)")
    List<Provincial> queryProvincial();



    @Select("select DISTINCT id, name, pid from (select id, name, pid from City where pid  in(230000,140000)) L join NowWeather R on L.name = R.areaDetail  where pid = #{provincialId}")
    List<Provincial> queryCity(@Param("provincialId") String provincialId);

    @Select("select id, name, pid from City where pid in(230000,140000)")
    List<Provincial> queryCity24hour();

    @Select("select * from (select id, name, pid from City where pid =  #{areaId}) L join NowWeather  R on L.name = R.areaName ")
    List<NowWeatherBean> queryArea(@Param("areaId") String areaId);

    @Select("SELECT `time`, areaCode, areaName, aqi, rain,sd, cast(temperature as unsigned) as temperature, temperaturetime, weather, weatherpic, winddirection, windpower, areaDetail, f1, c7 from NowWeather order by temperature desc limit 10")
    List<NowWeatherBean> queryAreaTop();

    @Select("SELECT `time`, areaCode, areaName, cast(aqi as unsigned) as aqi, rain, cast(sd as unsigned) as sd, temperature, temperaturetime, weather, weatherpic, winddirection, windpower, areaDetail, f1, c7 from NowWeather order by aqi asc limit 10")
    List<NowWeatherBean> queryAreaAqiTop();

    @Select("SELECT `time`, areaCode, areaName, aqi,  cast(rain as unsigned) as rain, sd, temperature, temperaturetime, weather, weatherpic, winddirection, windpower, areaDetail, f1, c7 FROM NowWeather order by rain desc limit 10")
    List<NowWeatherBean> queryAreaRainTop();

    @Select("SELECT `time`, areaCode, areaName, aqi,  rain, sd, temperature, temperaturetime, weather, weatherpic, winddirection, cast(windpower as unsigned) as windpower, areaDetail, f1, c7 FROM NowWeather order by windpower desc limit 10")
    List<NowWeatherBean> queryAreaWindpowerTop();





    @Select("select * from City where id = #{id}")
    List<Provincial> queryId(@Param("id") String id);

    @Select("select * from NowWeather where areaName = #{areaName}")
    List<NowWeatherBean> queryNowWeather(@Param("areaName") String areaName);

    @Select("select * from NowWeather where 1 = 1")
    List<NowWeatherBean> queryNowWeatherAll();

    @Select("select * from Future24Hour L join City R on L.areaName = R.name where id = #{areaName}")
    List<Future24Hour> queryFuture24Hour(String aeraId);


    @Select("select * from Future15Day L join City R on L.area = R.name where id = #{areaName}")
    List<Future15Day> queryFutureday15(String aeraId);

    @Select("select * from Future40Day L join City R on L.area = R.name where id = #{areaName}")
    List<Future15Day> queryFutureday40(String aeraId);





//    @Select("select * from NowWeather where areaName = #{areaName}")
//    List<NowWeatherBean> queryNowWeather(@Param("areaName") String areaName);





}

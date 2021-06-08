package com.bs.weather.bean
/**
 * @ClassName : WeatherBean
 * @Description : 现在实时的天气情况
 * @Author :
 * @Date: 2021-04-21 12:26
 */
case class NowWeather(
                       aqi: String, // 空气指数，越小越好
                       sd: String, // 空气湿度
                       temperature: String, // 气温
                       temperature_time: String, // 获得气温的时间
                       weather: String, // 天气
                       weather_pic: String, // 天气小图标
                       wind_direction: String, // 风向
                       wind_power: String, // 风力
                       aqiDetail: AqiDetail, //aqi明细数据
                       rain: String //	降水量（mm）
                     )
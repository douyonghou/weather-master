package com.bs.weather.bean

/**
 * 景点数据
 */
case class NowWeatherBean(
                           time: String,
                           areaCode: String,
                           areaName: String,
                           aqi: String,
                           rain: String,
                           sd: String,
                           temperature: String,
                           temperature_time: String,
                           weather: String,
                           weather_pic: String,
                           wind_direction: String,
                           wind_power: String,
                           areaDetail: String,
                           f1: String,
                           c7: String
                         )

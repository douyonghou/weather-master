package com.bs.weather.bean

case class ResBodyNow(
                       time: String, //预报发布时间
                       now: NowWeather, //现在实时的天气情况
                       f1: String
                     )

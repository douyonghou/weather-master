package com.bs.weather.bean

case class AqiDetail(
                      aqi: String, //	空气质量指数，越小越好
                      area: String, //	地区
                      co: String, // 一氧化碳1小时平均，mg/m3
                      no2: String, // 二氧化氮1小时平均,μg/m3
                      o3: String, // 臭氧1小时平均，μg/m3
                      o3_8h: String, // 臭氧8小时平均，μg/m3
                      pm10: String, // 颗粒物（粒径小于等于10μm）1小时平均，μg/m3
                      pm2_5: String, // 颗粒物（粒径小于等于2.5μm）1小时平均，μg/m3
                      primary_pollutant: String, // 首要污染物
                      quality: String, // 空气质量指数类别，有“优质、良好、轻度污染、中度污染、重度污染、严重污染”6类
                      so2: String, // 二氧化硫1小时平均，μg/m3
                      num: String // 排名。越小越好
                    )

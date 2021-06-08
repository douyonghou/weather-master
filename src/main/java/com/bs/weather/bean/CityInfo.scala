package com.bs.weather.bean

case class CityInfo(
                     c1: String, // 区域id
                     c2: String, // 城市英文名
                     c3: String, // 城市中文名
                     c4: String, // 城市所在市英文名
                     c5: String, // 城市所在市中文名
                     c6: String, // 城市所在省英文名
                     c7: String, // 城市所在省中文名
                     c8: String, // 城市所在国家英文名
                     c9: String, // 城市所在国家中文名
                     c10: String, // 城市级别
                     c11: String, // 城市区号
                     c12: String, // 邮编
                     longitude: String, // 经度
                     latitude: String, // 纬度
                     c15: String, // 海拔
                     c16: String, // 雷达站号
                     c17: String, // 时区
                     c0: String // 地区code
                   )

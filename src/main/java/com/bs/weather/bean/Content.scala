package com.bs.weather.bean

/**
 * 景点详细数据
 * @param id
 * @param areaId 镇id
 * @param address 景点地址
 * @param cityId 城市id
 * @param coupon
 * @param ct
 * @param summary 景点描述
 * @param areaName 镇名称
 * @param opentime
 * @param name 景点名称
 * @param cityName 城市名
 * @param content
 * @param price 最低门票价
 * @param attention
 * @param proName 省级名称
 * @param proId 省级id
 * @param location
 * @param picList 图片列表
 */
case class Content(
                    id: String,
                    areaId: String,
                    address: String,
                    cityId: String,
                    coupon: String,
                    ct: String,
                    summary: String,
                    areaName: String,
                    opentime: String,
                    name: String,
                    cityName: String,
                    content: String,
                    price: String,
                    attention: String,
                    proName: String,
                    proId: String,
                    location: String,
                    picList: String
                  )

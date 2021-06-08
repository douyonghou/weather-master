package com.bs.weather.bean

/**
 *
 * spark-streaming卡夫卡的连接信息
 * @param bootstrap kafka地址
 * @param zk zookerper地址
 * @param groupId 消费者
 * @param topics 消费主题
 * @param duration 批处理时间
 */
case class Ssp(
                bootstrap: String,
                zk: String,
                groupId: String,
                topics: Array[String],
                duration: Int
              )

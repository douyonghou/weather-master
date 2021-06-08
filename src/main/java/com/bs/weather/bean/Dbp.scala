package com.bs.weather.bean

/**
 * 数据库连接信息
 * @param poolName 连接池名称
 * @param driver 驱动类
 * @param url url
 * @param username 账号
 * @param password 密码
 */
case class Dbp(
                poolName: String,
                driver: String,
                url: String,
                username: String,
                password: String
              )

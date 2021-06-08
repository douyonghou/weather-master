package com.bs.weather.bean

case class Weather(
                    url: String,
                    appId: String,
                    appSecret: String
                  ){
  (url, appId, appSecret)
}

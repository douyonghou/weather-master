package com.bs.weather.util

import java.text.SimpleDateFormat
import java.util.Date

import com.alibaba.fastjson.JSON
import com.bs.weather.bean.JinDian
import com.show.api.ShowApiRequest

/**
 * @ClassName : GatewayApiService
 * @Description : 调用易用接口数
 * @Author : bs
 * @Date: 2021-04-19 22:03
 */
object SecenryGatewayApiService {
  /**
   * 当前日期
   */
  val now: Date = new Date()
  val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyyMMddHH")
  val date = dateFormat.format(now)

  /** *
   * 获取api景点数据
   * 返回：json
   */
  def getSceneryData(cityName: String = "泰山"): java.util.List[JinDian] = {
    val res: String = new ShowApiRequest("http://route.showapi.com/268-1", "576267", "55c832971f644a23bc1025d418df4760")
      .addTextPara("keyword",cityName)
      .post()
    // 景点数据列表
    val resJson: ResJson = JSON.parseObject(res, classOf[ResJson])
    val contens: String = resJson.showapi_res_body.pagebean.contentlist.replaceAll("\\\\n|\\\\t|\\\\r|\\s+", "")
    // 省粒度的各个景点数据
    val JinDianList: java.util.List[JinDian] = JSON.parseArray(contens, classOf[JinDian])
    return JinDianList
  }

  def main(args: Array[String]): Unit = {
   val list = getSceneryData()
    var k = 0
    (0 to list.size() - 1).foreach(i =>
      if(list.get(i).getPicList.length() > 6 ){
        k = k + 1
          println(list.get(i).getPicList.split("\":\"")(1).split(",")(0).replace("\"",""))
        println(list.get(i).getAddress)
        println(list.get(i).getAreaName)
        println(list.get(i).getCityName)
        println(list.get(i).getProName)
        println(list.get(i).getAttention)
        println(list.get(i).getContent)
        println(list.get(i).getName)
        println(list.get(i).getPrice)
        println(list.get(i).getLocation)
      }
    )

  }

}

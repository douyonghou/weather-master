package bean

/**
 * 景点数据
 */
case class Now(
                time: String, //预报发布时间
                now: String, //现在实时的天气情况
                f1: String,
                cityInfo: String
              )

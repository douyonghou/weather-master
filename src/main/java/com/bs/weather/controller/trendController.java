package com.bs.weather.controller;


import com.bs.weather.entity.AvgWeatherBean;
import com.bs.weather.service.AvgService;
import com.bs.weather.service.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/trend")
public class trendController {
    @Autowired
    TrendService trendService;
    /**
     *
     *
     * @param request
     * @return
     */
    @RequestMapping("/getTemperature")
    public Map<String, Object> getTemperature(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        List<String> timeTypeList = new ArrayList<>();
        List<Double> timeCntList = new ArrayList<>();
        List<AvgWeatherBean> timeList = trendService.getTrend();

        for (AvgWeatherBean weather: timeList) {
            System.out.println(weather.getDt());
            String type = weather.getDt();
            double cnt = weather.getTemperature();
            timeCntList.add(cnt);
            timeTypeList.add(type);
        }
        map.put("success", true);
        map.put("timeType", timeTypeList);
        map.put("timeCnt", timeCntList);
        return map;
    }

    /**
     *
     *
     * @param request
     * @return
     */
    @RequestMapping("/getsd")
    public Map<String, Object> getsd(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        List<String> timeTypeList = new ArrayList<>();
        List<Double> timeCntList = new ArrayList<>();
        List<AvgWeatherBean> timeList = trendService.getsd();

        for (AvgWeatherBean weather: timeList) {
            System.out.println(weather.getDt());
            String type = weather.getDt();
            double cnt = weather.getSd();
            timeCntList.add(cnt);
            timeTypeList.add(type);
        }
        map.put("success", true);
        map.put("timeType", timeTypeList);
        map.put("timeCnt", timeCntList);
        return map;
    }

    /**
     *
     *
     * @param request
     * @return
     */
    @RequestMapping("/getaqi")
    public Map<String, Object> getaqi(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        List<String> timeTypeList = new ArrayList<>();
        List<Double> timeCntList = new ArrayList<>();
        List<AvgWeatherBean> timeList = trendService.getaqi();

        for (AvgWeatherBean weather: timeList) {
            System.out.println(weather.getDt());
            String type = weather.getDt();
            double cnt = weather.getAqi();
            timeCntList.add(cnt);
            timeTypeList.add(type);
        }
        map.put("success", true);
        map.put("timeType", timeTypeList);
        map.put("timeCnt", timeCntList);
        return map;
    }

    /**
     *
     *
     * @param request
     * @return
     */
    @RequestMapping("/getwindpower")
    public Map<String, Object> getwindpower(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        List<String> timeTypeList = new ArrayList<>();
        List<Double> timeCntList = new ArrayList<>();
        List<AvgWeatherBean> timeList = trendService.getwindpower();

        for (AvgWeatherBean weather: timeList) {
            System.out.println(weather.getDt());
            String type = weather.getDt();
            double cnt = weather.getWindpower();
            timeCntList.add(cnt);
            timeTypeList.add(type);
        }
        map.put("success", true);
        map.put("timeType", timeTypeList);
        map.put("timeCnt", timeCntList);
        return map;
    }


}

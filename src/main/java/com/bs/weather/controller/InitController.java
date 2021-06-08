package com.bs.weather.controller;


import com.bs.weather.entity.*;
import com.bs.weather.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/init")
public class InitController {
    @Autowired
    AreaService areaService;
    @Autowired
    NowService nowService;
    @Autowired
    UnitKindService unitKindService;

    /**
     * 现在实时天气预报
     *
     * @param request
     * @return
     */
    @RequestMapping("/getinit")
    public Map<String, Object> getinit(@RequestParam(value = "provincialId", required = false) String provincialId,
                                       @RequestParam(value = "cityId", required = false) String cityId,
                                       @RequestParam(value = "aeraId", required = false) String aeraId,
                                       HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(7);
        // 获取省级信息
        Map<String, Provincial> provincialMap = new HashMap<String, Provincial>();
        List<Provincial> provincialList = nowService.getProvincial();

        // 获取地区信息
        List<Provincial> cityList = nowService.getCity(provincialId);
        System.out.println(provincialId+"++++++++++++++++");

        // 获取县信息
        List<NowWeatherBean> areaList = nowService.getArea(cityId);

        // 获取县信息
        List<Future24Hour> hour24List = nowService.gethour24(aeraId);

        // day15
        List<Future15Day> day15List = nowService.getday15(aeraId);
        System.out.println(aeraId+"-----------------");

        List<Future15Day> day40List = nowService.getday40(aeraId);
        for (Future15Day area : day40List) {
            System.out.println(area.getArea());
        }


        map.put("provincial", provincialList); //省
        map.put("city", cityList); //区
        map.put("List", areaList); //县
        map.put("hour24List", hour24List); //一个县的24小时
        map.put("day15List", day15List); //一个县的24小时
        map.put("day40List", day40List); //一个县的24小时
        map.put("success", true);

        return map;
    }


    /**
     * 现在实时天气预报
     *
     * @param request
     * @return
     */
    @RequestMapping("/gettop")
    public Map<String, Object> gettop(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(5);
        // 获取县信息
        List<NowWeatherBean> areaList = nowService.getAreaTop();
        List<NowWeatherBean> areaaqiList = nowService.getAreaaqiTop();
        List<NowWeatherBean> arearainList = nowService.getArearainTop();
        List<NowWeatherBean> windpowerList = nowService.getAreawindpowerTop();
        for (NowWeatherBean area : areaList) {
            System.out.println(area.getAreaName());
        }
        map.put("List", areaList); //湿度
        map.put("Listaqi", areaaqiList); //空气质量
        map.put("ListRain", arearainList); //空气质量
        map.put("ListWindpower", windpowerList); //风力
        map.put("success", true);
        return map;
    }


    private Map<String, Object> getArea(Integer areaId) {
        Map<String, Object> map = new HashMap<>(5);
        if (areaId == 0) {
            List<Area> area = areaService.getArea(null);
            map.put("success", true);
            map.put("areaList", area);
            return map;
        }
        if (areaId < 0) {
            map.put("success", false);
            map.put("errMsg", "参数错误");
            return map;
        } else {
            Area area = new Area();
            area.setParentId(areaId);
            List<Area> areaList = areaService.getArea(area);
            if (areaList.size() > 0) {
                map.put("success", true);
                map.put("areaList", areaList);
                return map;
            } else {
                Area area1 = new Area();
                area1.setAreaId(areaId);
                List<Area> areaList1 = areaService.getArea(area1);
                map.put("success", true);
                map.put("areaList", areaList1);
                return map;
            }
        }
    }

}

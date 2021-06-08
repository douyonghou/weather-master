package com.bs.weather.controller;


import com.bs.weather.entity.*;
import com.bs.weather.service.AreaService;
import com.bs.weather.service.AvgService;
import com.bs.weather.service.NowService;
import com.bs.weather.service.UnitKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/avg")
public class avgController {
    @Autowired
    AvgService avgService;
    /**
     * top
     *
     * @param request
     * @return
     */
    @RequestMapping("/getavg")
    public Map<String, Object> getavg(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(2);
        List<AvgWeatherBean> avgList = avgService.getAvg();

        map.put("List", avgList); //avg
        map.put("success", true);
        return map;
    }

}

package com.bs.weather.controller;

import com.bs.weather.bean.JinDian;
import com.bs.weather.util.Content;
import com.bs.weather.util.SecenryGatewayApiService;
import com.bs.weather.util.SecenryGatewayApiService$;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-03-08 19:52
 */
@RestController
@RequestMapping("/jingdian")
public class JingdianController {

    /**
     * 景点
     * @return
     */
    @RequestMapping(value = "/keyword", method = RequestMethod.GET)
    public Map<String, Object> keyword(@RequestParam("keyword") String jdKeyword){
        Map<String, Object> map = new HashMap<>(1);
        System.out.println("--------------" + jdKeyword);
        List<JinDian> contentList = SecenryGatewayApiService.getSceneryData(jdKeyword);

        List<JinDian> contentListN = new ArrayList<JinDian>();
        for(JinDian content : contentList){
            if(content.getPicList().length() > 6 ){
                System.out.println(content.getPicList().split("\":\"")[1].split(",")[0].replace("\"",""));
                content.setPicList(content.getPicList().split("\":\"")[1].split(",")[0].replace("\"",""));
                contentListN.add(content);
            }
        }


        map.put("contentList", contentListN);
        return map;
    }
    /**
     * 路线
     * @return
     */
    @RequestMapping(value = "/route", method = RequestMethod.GET)
    public static Map<String, Object> route(@RequestParam("keyword") String jdKeyword){
        Map<String, Object> map = new HashMap<>(1);
        System.out.println("--------------" + jdKeyword);
        List<JinDian> contentList = SecenryGatewayApiService.getSceneryData(jdKeyword);
        int k = 0;
        List<JinDian> contentListN = new ArrayList<JinDian>();

        for(JinDian content : contentList){
            int len = contentList.size();
            int ran = (int) (new Random().nextInt( 5));
            String fullName = content.getProName() + content.getCityName() + content.getAreaName();
            if(!fullName.contains("nu")){
                k ++ ;
                content.setIndex(k+"");
                String name = content.getName();
                if(ran == 0){
                    name = name + "✳✳✳";
                    System.out.println(name + "-------------");
                }
                content.setFullName(content.getProName() + content.getCityName() + content.getAreaName());
                content.setName(name);
                content.setIndexNew(( 1 + new Random().nextInt( len -1 )) + "->" + ( 1 + new Random().nextInt( len -1)));

                contentListN.add(content);

                System.out.println(
                        content.getProName() +
                                content.getCityName() +
                                content.getAreaName() +
                                name

                );
            }

        }


        map.put("routeList", contentListN);
        return map;
    }


}








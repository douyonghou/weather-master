package com.bs.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version 1.0
 */
@Controller
@RequestMapping("/page")
public class PageController {
    /**
     * 进入首页界面 权限 0，1，2
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "/html/index.html";
    }

    /**
     * 进入图表 界面 权限 0，1，2
     *
     * @return
     */
    @RequestMapping("/route")
    public String route() {
        return "/html/route.html";
    }


//    天气
    /**
     * 实时天气预报
     * @return
     */
    @RequestMapping("/nowweather")
    public String nowweather() {
        return "/html/now-listN.html";
    }

    /**
     * 排名
     * @return
     */
    @RequestMapping("/weathertop")
    public String weathertop() {
        return "/html/top-listN.html";
    }

    /**
     * 排名
     * @return
     */
    @RequestMapping("/weatheraqitop")
    public String weatheraqitop() {
        return "/html/topaqi-listN.html";
    }


    /**
     * 排名
     * @return
     */
    @RequestMapping("/weatherraintop")
    public String weatherraintop() {
        return "/html/toprain-listN.html";
    }

    /**
     * 排名
     * @return
     */
    @RequestMapping("/weatherwindpowertop")
    public String weatherwindpowertop() {
        return "/html/topwindpower-listN.html";
    }


    /**
     * 排名
     * @return
     */
    @RequestMapping("/weatheravg")
    public String weatheravg() {
        return "/html/avg-listN.html";
    }

    /**
     * 趋势
     * @return
     */
    @RequestMapping("/weathertrend")
    public String weathertrend() {
        return "/html/weathertrend.html";
    }


    /**
     * 指数查询
     * @return
     */
    @RequestMapping("/weatherindex")
    public String weatherindex() {
        return "/html/index-listN.html";
    }

    /**
     * 未来
     * @return
     */
    @RequestMapping("/hour24")
    public String hour24() {
        return "/html/hour24-listN.html";
    }

    /**
     * 未来
     * @return
     */
    @RequestMapping("/day15")
    public String day15() {
        return "/html/day15-listN.html";
    }

    /**
     * 未来
     * @return
     */
    @RequestMapping("/day40")
    public String day40() {
        return "/html/day40-listN.html";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "/html/loginF.html";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/collegeadd")
    public String collegeAdd() {
        return "/html/college-add.html";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/collegeedit")
    public String collegeEdit() {
        return "/html/college-edit.html";
    }

    /**
     * @return
     */
    @RequestMapping("/specialtylist")
    public String specialtyList() {
        return "/html/specialty-list.html";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/specialtyadd")
    public String specialtyAdd() {
        return "/html/specialty-add.html";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/specialtyedit")
    public String specialtyEdit() {
        return "/html/specialty-edit.html";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/classgradelist")
    public String classgradeList() {
        return "/html/classgrade-list.html";
    }

    /**
     * @return
     */
    @RequestMapping("/classgradeadd")
    public String classgradeAdd() {
        return "/html/classgrade-add.html";
    }


    /**
     * @return
     */
    @RequestMapping("/classgradeedit")
    public String classgradeEdit() {
        return "/html/classgrade-edit.html";
    }

    /**
     * @return
     */
    @RequestMapping("/error")
    public String error(){
        return "/html/error.html";
    }

    /**
     * @return
     */
    @RequestMapping("/person_0")
    public String person0List(){
        return "/html/person_0-list.html";
    }

    /**
     * @return
     */
    @RequestMapping("/person_o_add")
    public String person0Add(){
        return "/html/person_o_add.html";
    }

    /**
     * @return
     */
    @RequestMapping("/person_0_edit")
    public String person0Edit(){
        return "/html/person_0-edit.html";
    }

    /**
     * @return
     */
    @RequestMapping("/person_1_list")
    public String person1List(){
        return "/html/person_1-list.html";
    }

    /**
     * @return
     */
    @RequestMapping("/person1add")
    public String  person1Add(){
        return "/html/person_1_add.html";
    }

    /**
     * @return
     */
    @RequestMapping("/person_1_edit")
    public String person1Edit(){
        return "/html/person_1-edit.html";
    }

    /**
     * @return
     */
    @RequestMapping("/getinfo")
    public String getInfo(){
        return "/html/getinfo.html";
    }

    /**
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(){
        return "/html/welcome.html";
    }

    /**
     * @return
     */
    @RequestMapping("/personedit")
    public String personedit(){return "/html/personedit.html";}

    /**
     * @return
     */
    @RequestMapping("/toexcal")
    public String toExcal(){return "/html/toExcal.html";}

    /**
     * @return
     */
    @RequestMapping("/jingdian")
    public String jingdian(){return "/html/jingdian.html";}


    @RequestMapping("/fase")
    public String fase(){return "/html/fase.html";}

    @RequestMapping("/faseLogin")
    public String faseLogin(){return "/html/faseLogin.html";}



}


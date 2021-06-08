package com.bs.weather.entity;

/**
 * @ClassName : NowWeatherBean
 * @Description : NowWeatherBean
 * @Author : douyonghou
 * @Date: 2021-04-28 13:16
 */
public class NowWeatherBean {

    String time;
    String areaCode;
    String areaName;
    String aqi;
    String rain;
    String sd;
    String temperature;
    String temperaturetime;
    String weather;
    String weatherpic;
    String winddirection;
    String windpower;
    String areaDetail;
    String f1;
    String c7;

    public String getTime() {
        return time.substring(0, 4) + "-" + time.substring(4, 6)+ "-" + time.substring(6, 8) +" "+ temperaturetime;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperaturetime() {
        return temperaturetime;
    }

    public void setTemperaturetime(String temperaturetime) {
        this.temperaturetime = temperaturetime;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherpic() {
        return weatherpic;
    }

    public void setWeatherpic(String weatherpic) {
        this.weatherpic = weatherpic;
    }

    public String getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getAreaDetail() {
        return areaDetail;
    }

    public void setAreaDetail(String areaDetail) {
        this.areaDetail = areaDetail;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getC7() {
        return c7;
    }

    public void setC7(String c7) {
        this.c7 = c7;
    }

    public static void main(String[] args) {
        System.out.println("1234567".substring(0,3));
    }

}

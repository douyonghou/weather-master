package com.bs.weather.entity;

/**
 * @ClassName : NowWeatherBean
 * @Description : NowWeatherBean
 * @Author : douyonghou
 * @Date: 2021-04-28 13:16
 */
public class AvgWeatherBean {
    String dt;
    String areaName;
    double temperature;
    double aqi;
    double windpower;
    double sd;

    public double getSd() {
        return sd;
    }

    public void setSd(double sd) {
        this.sd = sd;
    }

    public String getDt() {
        try {
            return dt.substring(0,4) + "-" + dt.substring(4,6) + "-" + dt.substring(6,8) ;
        }catch (NullPointerException e){
            return dt;
        }


    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getAqi() {
        return aqi;
    }

    public void setAqi(double aqi) {
        this.aqi = aqi;
    }

    public double getWindpower() {
        return windpower;
    }

    public void setWindpower(double windpower) {
        this.windpower = windpower;
    }
}

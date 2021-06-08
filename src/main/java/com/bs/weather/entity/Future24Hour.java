package com.bs.weather.entity;

/**
 * @ClassName : Future24Hour
 * @Description : 未来24小时
 * @Author : douyonghou
 * @Date: 2021-04-28 22:31
 */
public class Future24Hour {
    String areaName;
    String areaid;
    String weather;
    String temperature;
    String winddirection;
    String time;
    String weathercode;
    String windpower;

    public String getArea() {
        return areaName;
    }

    public void setArea(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    public String getTime() {
        return "【" + time.substring(0, 8)  + "】-【" + time.substring(8, 10) + "时】";
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeathercode() {
        return weathercode;
    }

    public void setWeathercode(String weathercode) {
        this.weathercode = weathercode;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }
}

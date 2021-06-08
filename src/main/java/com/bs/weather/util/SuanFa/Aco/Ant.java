package com.bs.weather.util.SuanFa.Aco;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Ant implements Cloneable {
    // 禁忌表
    private Vector<Integer> tabu;
    // allowedk
    private Vector<Integer> allowedCities;
    // 信息素变化矩阵
    private float[][] delta;
    // 距离矩阵
    private float[][] distance;
    private float alpha;
    private float beta;
    // 走过的路径长度
    private int tourLength;
    // 城市数量
    private int cityNum;
    // 起始城市
    private int firstCity;

    // 当前城市
    private int currentCity;

    /**
     * Constructor of Ant
     *
     * @param num
     *            城市数量
     */
    public Ant(int num, float[][] distance, float alpha, float beta) {
        cityNum = num;
        tourLength = 0;
        this.alpha = alpha;
        this.beta = beta;
        init(distance);
    }

    /**
     * 初始化蚂蚁，随机选择起始位置
     *
     * @param distance
     *                  距离矩阵
     */

    public void init(float[][] distance) {

        //随机选择出发城市，currentCity初始化
        currentCity = firstCity = new Random().nextInt(cityNum);

        // 初始允许搜索的城市集合
        allowedCities = new Vector<>();

        // 初始禁忌表
        tabu = new Vector<>();

        // 初始距离矩阵
        this.distance = distance;

        // 初始信息素变化矩阵为0 & 初始化可达城市
        delta = new float[cityNum][cityNum];
        for (int i = 0; i < cityNum; i++) {
            if (distance[firstCity][i] != 0 && distance[firstCity][i] != Float.MAX_VALUE) {
                allowedCities.add(i);
            }
            for (int j = 0; j < cityNum; j++) {
                delta[i][j] = 0.f;
            }
        }

        // 将起始城市添加至禁忌表
        tabu.add(firstCity);
    }

    /**
     *
     * 选择下一个城市
     *
     * @param pheromone
     *            信息素矩阵
     */

    public boolean selectNextCity(float[][] pheromone) {
        //标识位
        boolean mark=true;

        //轮盘赌选出的城市
        int selectCity = 0;

        //概率矩阵
        float[] p = new float[cityNum];

        //sum分母
        float sum = 0.0f;

        // 计算sum
        for (Integer i : allowedCities) {
            sum += Math.pow(pheromone[currentCity][i], alpha)
                    * Math.pow(1.0 / distance[currentCity][i], beta);
        }

        // 计算概率矩阵
        for (Integer allowedCity : allowedCities) {
            p[allowedCity] = (float) (Math.pow(pheromone[currentCity][allowedCity], alpha) * Math
                    .pow(1.0 / distance[currentCity][allowedCity], beta)) / sum;
        }

        // 轮盘赌选择下一个城市
        float sleectP = new Random().nextFloat();
        float sum1 = 0.f;
        for (Integer i : allowedCities) {
            sum1 += p[i];
            if (sum1 >= sleectP) {
                selectCity = i;
                break;
            }
        }

        //更新可达城市
        allowedCities.clear();
        for (int i = 0; i < cityNum; i++) {
            //当前城市可以到i城市，并且i城市也没被走过
            if (distance[selectCity][i] != 0 && distance[selectCity][i] != Float.MAX_VALUE &&!tabu.contains(i)) {
                allowedCities.add(i);
            }
        }

        // 在禁忌表中添加select city
        tabu.add(selectCity);

        // 将当前城市改为选择的城市
        currentCity = selectCity;

        //是否进入死路
        if (allowedCities.size()==0 && tabu.size()<cityNum){
            mark = false;
        }

        //返回标示位
        return mark;
    }

    /**
     * 计算路径长度
     *
     * @return 路径总长度
     */
    private int calculateTourLength() {
        int len = 0;
        //禁忌表tabu最终形式：起始城市,城市1,城市2...城市n
        for (int i = 0; i < tabu.size() - 1; i++) {
            len += distance[this.tabu.get(i)][this.tabu.get(i + 1)];
        }
        return len;
    }

    public Vector<Integer> getAllowedCities() {
        return allowedCities;
    }

    public void setAllowedCities(Vector<Integer> allowedCities) {
        this.allowedCities = allowedCities;
    }

    public int getTourLength() {
        tourLength = calculateTourLength();
        return tourLength;
    }

    public void setTourLength(int tourLength) {
        this.tourLength = tourLength;
    }

    public int getCityNum() {
        return cityNum;
    }

    public void setCityNum(int cityNum) {
        this.cityNum = cityNum;
    }

    public Vector<Integer> getTabu() {
        return tabu;
    }

    public void setTabu(Vector<Integer> tabu) {
        this.tabu = tabu;
    }

    public float[][] getDelta() {
        return delta;
    }

    public void setDelta(float[][] delta) {
        this.delta = delta;
    }

    public int getFirstCity() {
        return firstCity;
    }

    public void setFirstCity(int firstCity) {
        this.firstCity = firstCity;
    }
}
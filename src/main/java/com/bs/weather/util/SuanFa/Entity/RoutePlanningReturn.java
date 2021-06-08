package com.bs.weather.util.SuanFa.Entity;

import java.util.List;

public class RoutePlanningReturn {

    private int bestLength; // aco最佳长度
    private int[] bestTour; // aco最佳路径
    List<String> diePoint;  // 不满足条件城市
    private int[] minTree;  // 朱刘


    public List<String> getDiePoint() {
        return diePoint;
    }

    public void setDiePoint(List<String> diePoint) {
        this.diePoint = diePoint;
    }

    public int getBestLength() {
        return bestLength;
    }

    public void setBestLength(int bestLength) {
        this.bestLength = bestLength;
    }

    public int[] getBestTour() {
        return bestTour;
    }

    public void setBestTour(int[] bestTour) {
        this.bestTour = bestTour;
    }

    public int[] getMinTree() {
        return minTree;
    }

    public void setMinTree(int[] minTree) {
        this.minTree = minTree;
    }

}

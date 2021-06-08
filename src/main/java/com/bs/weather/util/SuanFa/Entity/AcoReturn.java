package com.bs.weather.util.SuanFa.Entity;

public class AcoReturn {
    // 最佳长度
    private int bestLength;
    // 最佳路径
    private int[] bestTour;

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
}

package com.bs.weather.util.SuanFa.Entity;


import java.util.List;

public class SetNewMapReturn {
    protected float[][] distanceTmp;              //更新后的权值矩阵
    protected int firstPointTmp;                  //更新后的新起点索引
    protected int[] newIndexArray;                //更新后的新索引对应关系数组
    protected int[] minPointArrayIn;              //入边存储
    protected int[] minPointArrayOut;             //出边存储
    protected List<Integer> allRingList1;         //环集（只存储当前环的数据）


    public List<Integer> getAllRingList() {
        return allRingList1;
    }

    public void setAllRingList(List<Integer> allRingList) {
        this.allRingList1 = allRingList;
    }

    public int[] getMinPointArrayIn() {
        return minPointArrayIn;
    }

    public void setMinPointArrayIn(int[] minPointArrayIn) {
        this.minPointArrayIn = minPointArrayIn;
    }

    public int[] getMinPointArrayOut() {
        return minPointArrayOut;
    }

    public void setMinPointArrayOut(int[] minPointArrayOut) {
        this.minPointArrayOut = minPointArrayOut;
    }

    public int getFirstPointTmp() {
        return firstPointTmp;
    }

    public void setFirstPointTmp(int firstPointTmp) {
        this.firstPointTmp = firstPointTmp;
    }

    public int[] getNewIndexArray() {
        return newIndexArray;
    }

    public void setNewIndexArray(int[] newIndexArray) {
        this.newIndexArray = newIndexArray;
    }

    public float[][] getDistanceTmp() {
        return distanceTmp;
    }

    public void setDistanceTmp(float[][] distanceTmp) {
        this.distanceTmp = distanceTmp;
    }
}

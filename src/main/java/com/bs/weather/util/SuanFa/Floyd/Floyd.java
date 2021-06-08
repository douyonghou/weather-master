package com.bs.weather.util.SuanFa.Floyd;

import java.util.ArrayList;
import java.util.List;

public class Floyd {
    private float[][][] distancePlus;   //三维矩阵-距离-中转点
    private float[][] haveK;    //中转点矩阵

    public Floyd(float[][] distance) {
        init(distance);
    }

    public void init(float[][] distance) {
        haveK = new float[distance.length][distance.length];
        this.distancePlus = new float[][][]{distance, haveK};
        //初始化haveK
        for (int i = 0; i < haveK.length; i++) {
            for (int j = 0; j < haveK.length; j++) {
                haveK[i][j] = -1;
            }
        }
    }

    //floyd算法
    public void runFloyed() {
        for (int k = 0; k < distancePlus[0].length; k++) {
            for (int i = 0; i < distancePlus[0].length; i++) {
                for (int j = 0; j < distancePlus[0].length; j++) {
                    if (distancePlus[0][i][j] > distancePlus[0][i][k] + distancePlus[0][k][j]) {
                        //更新最短距离
                        distancePlus[0][i][j] = distancePlus[0][i][k] + distancePlus[0][k][j];
                        //更新中转点
                        distancePlus[1][i][j] = k;
                    }
                }
            }
        }
    }

    //输入任意两个地点给出该图中最短路线
    public List getFloyd(int startCity, int endCity) {
        //kKlist中的元素是路线的倒叙
        List<Integer> kList = new ArrayList<>();
        kList.add(endCity);
        for (int i = 0; i < distancePlus[1].length; i++) {
            if ((int) distancePlus[1][startCity][endCity] != -1) {
                endCity = (int) distancePlus[1][startCity][endCity];
                kList.add(endCity);
            }
        }
        kList.add(startCity);
//        printFloyd();
        return kList;
    }
    //最短距离矩阵和中转点矩阵输出
    public void printFloyd(){
        //中转点
            for(float[] i : distancePlus[1]){
                for (float j: i) {
                    //路径向上收敛
                if (j!=-1){

                }
                        System.out.printf("%-10s",(int)j+"     ");
                }
                System.out.println();
            }

        System.out.println(); System.out.println(); System.out.println();

        //最短距离矩阵
        for(float[] i : distancePlus[0]){

            for (float j: i) {
                    System.out.printf("%-10s",j+"     ");
            }
            System.out.println();
        }

    }
}

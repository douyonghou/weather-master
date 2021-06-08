package com.bs.weather.util.SuanFa;




import com.bs.weather.util.SuanFa.Entity.RoutePlanningReturn;
import com.bs.weather.util.SuanFa.RoutePlan.RoutePlanning;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        float INF = Float.MAX_VALUE;
//        //初始化城市距离----对称---互相可达----矩阵
//        float[][] distance = new float[][]{
//                {0, 1, 5, 6},
//                {1, 0, 2, 1},
//                {5, 2, 0, 10},
//                {6, 1, 10, 0}};

//        //初始化城市距离---对称---非互相可达----矩阵
//        float[][] distance = new float[][]{
//                {0, 1, 0, 6},
//                {1, 0, 3, 0},
//                {0, 3, 0, 10},
//                {6, 0, 10, 0}};

//        //初始化城市距离----非对称----互相可达-----矩阵
//        float[][] distance = new float[][]{
//                {0, 1, 5, 6},
//                {2, 0, 2, 1},
//                {9, 1, 0, 10},
//                {6, 7, 8, 0}};

//        //初始化城市距离---非对称---非互相可达----矩阵
//        float[][] distance = new float[][]{
//                {0, 1, 5, 6},
//                {0, 0, 2, 1},
//                {4, 3, 0, 0},
//                {5, 0, 10, 0}};

//        float[][] distance = new float[][]{
//                {0, 8, 4, 8, INF, INF, INF},
//                {8, 0, INF, 1, INF, INF, 20},
//                {4, INF, 0, 11, 2, 10, INF},
//                {8, 1, 11, 0, INF, INF, INF},
//                {INF, INF, 2, INF, 0, 7, 6},
//                {INF, INF, 10, INF, 7, 0, INF},
//                {INF, 20, INF, INF, 6, INF, 0}};

//        //不符合要求的数据，符合要求的数据为每个节点都有入边和出边，其他均为不符合要求的数据，会要求旅客重新输入
//        float[][] distance = new float[][]{
//                { 0, 1, 1, INF, INF, INF},
//                { INF, 0, INF, 1, INF, INF},
//                { INF, INF, 0, 1, 1, INF},
//                { 1, INF, INF, 0, INF, INF},
//                { INF, INF, INF, INF, 0, INF},
//                { INF, INF, INF, INF, INF, 0}};


        //运行时间查看
        long starttime = System.currentTimeMillis();
        float sum = 0.f;

//        //中期报告矩阵
//        float[][] distance = new float[][]{
//                {0,8,INF,6,INF,INF,INF},
//                {10,0,INF,3,INF,INF,20},
//                {4,INF,0,11,2,10,INF},
//                {8,1,9,0,INF,INF,INF},
//                {INF,INF,1,INF,0,7,6},
//                {INF,INF,12,INF,INF,0,INF},
//                {INF,18,INF,INF,6,INF,0}
//        };

        //distance：传入的距离矩阵（朱刘谷歌数据）
        float[][] distance = new float[][]{
                {INF, INF, INF, INF, INF, INF, INF},
                {INF, 0, 3, 9, INF, INF, INF},
                {INF, 7, 0, INF, INF, 9, 6},
                {INF, INF, 8, 0, INF, 5, INF},
                {INF, INF, INF, 4, 0, INF, INF},
                {INF, INF, INF, INF, 3, 0, 4},
                {INF, INF, 4, INF, INF, 8, 0}};
        //cityList：传入参数城市名称集合,（注意城市数量要和distance距离矩阵节点数对应上）,前段要做校验不可以加入重复城市
        List<String> cityList = new ArrayList<>();
        cityList.add("故宫");
        cityList.add("鸟巢");
        cityList.add("天坛");
        cityList.add("颐和园");
        cityList.add("长安街");
        cityList.add("圆明园");
        cityList.add("什刹海");
        //返回参数
        RoutePlanningReturn routePlanningReturn;

        RoutePlanning routePlanning = new RoutePlanning();
        //所有方案函数，传入参数distance与cityList
        routePlanningReturn = routePlanning.initRunRoutePlanning(distance,cityList);
        //所有方案结果输出函数，方案三会在里面二次调用，二次调用以后才会输出方案三的结果
        routePlanning.printResult(routePlanningReturn,routePlanning);


        long endtime = System.currentTimeMillis();
        sum += endtime - starttime;
        System.out.println("运行时间为" + sum / 1000.f + "秒");
    }
}

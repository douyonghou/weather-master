package com.bs.weather.util.SuanFa.RoutePlan;


import com.bs.weather.util.SuanFa.Aco.Aco;
import com.bs.weather.util.SuanFa.Entity.AcoReturn;
import com.bs.weather.util.SuanFa.Floyd.Floyd;
import com.bs.weather.util.SuanFa.Entity.RoutePlanningReturn;
import com.bs.weather.util.SuanFa.ZhuLiu_TarJan.ZhuLiu;


import java.util.ArrayList;
import java.util.List;

public class RoutePlanning {

    Aco aco;
    ZhuLiu zhuLiu;
    Floyd floyd;
    RoutePlanningReturn routePlanningReturn;
    List<String> cityList;

    public RoutePlanningReturn initRunRoutePlanning(float[][] distance, List cityList) {
        this.cityList = cityList;
        //首先对distace进行是否每个节点都有入边和出边的校验
        List<String> diePoint = checkDistance(distance, cityList);
        if (diePoint.size() == 0) {
            //所有节点都符合要求
            //返回参数初始化
            routePlanningReturn = new RoutePlanningReturn();
            //首先调用蚁群算法产生Tsp问题路径，并返回路径
            aco = new Aco(distance);
            AcoReturn acoReturn = aco.solve();
            //返回参数封装+调用
            routePlanningReturn.setBestLength(acoReturn.getBestLength());
            routePlanningReturn.setBestTour(acoReturn.getBestTour());

            //朱刘算法会改变distance所以这里引入一个新的对象来传入朱刘算法。
            float[][] distanceZhuLiu = new float[distance.length][distance.length];
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance.length; j++) {
                    distanceZhuLiu[i][j] = distance[i][j];
                }
            }
            //调用朱刘算法(tarjan算法)生成最小树形图（传入距离矩阵和蚁群算法的起点）
            zhuLiu = new ZhuLiu(distanceZhuLiu, routePlanningReturn.getBestTour()[0]);
            //返回参数封装+调用
            routePlanningReturn.setMinTree(zhuLiu.runZhuLiu());

            //调用floyd算法-让旅客查询任意两点间最短路径。
            floyd = new Floyd(distance);
            floyd.runFloyed();
            return routePlanningReturn;
        } else {
            //返回不满足条件的节点
            routePlanningReturn = new RoutePlanningReturn();
            routePlanningReturn.setDiePoint(diePoint);
            return routePlanningReturn;
        }
    }

    //查询任意两点间距离最短方法
    public List getFloyd(String startPoint, String endPoint) {
        int startPonitIndex = -1;
        int endPointIndex = -1;
        for (int i = 0; i < cityList.size(); i++) {
            if (startPoint.equals(cityList.get(i))) {
                startPonitIndex = i;
            }
            if (endPoint.equals(cityList.get(i))) {
                endPointIndex = i;
            }
        }
        if (startPonitIndex != -1 && endPointIndex != -1) {
            return floyd.getFloyd(startPonitIndex, endPointIndex);
        } else {
            return null;
        }
    }

    //distace校验，每条边必须有出边和入边否则就很直接返回给前端提示某一个城市路径不符合和要求，一般几乎不会出现这种情况。
    public List<String> checkDistance(float[][] distance, List cityList) {

        //存储不可达城市集合
        ArrayList<String> dieCity = new ArrayList<>();
        //标志数组为true的证明该节点有入边
        boolean[] diePonitArrayIn = new boolean[distance.length];
        //标志数组为true的证明该节点有出边
        boolean[] diePonitArrayOut = new boolean[distance.length];
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance.length; j++) {
                //选取没有入边的节点
                if (i != j) {
                    //j1为判断是否便利了所有节点的标志
                    int j1 = distance.length - 1;
                    if (i == distance.length - 1) {
                        //如果i或j有一个走到了最后一个节点那么遍历到distance.length-2即可
                        j1 = distance.length - 2;
                    }
                    if (distance[j][i] != Float.MAX_VALUE) {
                        diePonitArrayIn[i] = true;
                    } else if (j == j1 && !diePonitArrayIn[i] && !dieCity.contains(cityList.get(i))) {
                        //产生了没有入边的节点
                        dieCity.add((String) cityList.get(i));
                    }
                    if (distance[i][j] != Float.MAX_VALUE) {
                        diePonitArrayOut[i] = true;
                    } else if (j == j1 && !diePonitArrayOut[i] && !dieCity.contains(cityList.get(i))) {
                        //产生了没有出边的节点
                        dieCity.add((String) cityList.get(i));
                    }
                }
            }
        }
        return dieCity;
    }

    public void printResult(RoutePlanningReturn routePlanningReturn, RoutePlanning routePlanning) {

        if (routePlanningReturn.getDiePoint() == null) {
            //diePoint=null证明没有不满足条件的节点
            //返回结果变量
            int bestLength = routePlanningReturn.getBestLength();
            int[] bestTour = routePlanningReturn.getBestTour();
            int[] zhuLiuResult = routePlanningReturn.getMinTree();
            //方案三函数真正的调用，返回任意两点间距离，注意前端校验集合中没有的城市不可以调用并弹出提示
            List kList = routePlanning.getFloyd("故宫", "鸟巢");
            //由于三个方案拿到的结果都是cityList集合索引所以需要转化为城市名字，在输出时将体现
            //aco输出
            System.out.println("方案一（所有节点都联通且只经过一次）：");
            System.out.println("路径总长度：" + bestLength + "KM");
            System.out.print("游览顺序: ");
            if (bestTour[bestTour.length - 1] == -1) {
                for (int i = 0; i < bestTour.length - 1; i++) {
                    System.out.print(cityList.get(bestTour[i]) + "-->");
                }
                System.out.println("（节点间非互相可达无法形成环路）");
            } else {
                for (int i = 0; i < bestTour.length; i++) {
                    System.out.print(cityList.get(bestTour[i]) + "---->");
                }
                System.out.println("（可以形成环路）");
            }

            //zhuliu输出
            System.out.println("方案二（所有节点都连通并总体路径最短）");
            for (int i = 0; i < zhuLiuResult.length; i++) {
                if (zhuLiuResult[i] != -1) {
                    //起点不可以有入边，最终只有起点索引位置的值为-1，也就相当于直接过滤起点
                    System.out.println(cityList.get(zhuLiuResult[i]) + "---->" + cityList.get(i));
                }
            }

            //任意两点见最短路径输出
            //输出前先对刚才调用方案三函数的返回结果进行校验，查看传入的参数是否正确
            if (kList == null) {
                //输入的起点和终点不存在城市集合里
                System.out.println("方案三（任意两点间最短路线查询：）");
                System.out.println("输入的起点和终点不存在城市集合里，请检查并重新输入");
                return;
            }
            System.out.println("方案三（任意两点间最短路线查询：）");
            System.out.print(cityList.get((int) kList.get(kList.size() - 1)) + "到" + cityList.get((int) kList.get(0)) + "最短路线为:");
            for (int i = 0; i < kList.size(); i++) {
                System.out.print(cityList.get((int) (kList.get(kList.size() - i - 1))) + "--->");
            }
            System.out.println("(最短路径构造成功)");
        } else {
            System.out.println("路线规划失败，不满足路径要求的景点有");
            for (String i : routePlanningReturn.getDiePoint()) {
                System.out.println(i);
            }
            System.out.println("请删除以上景点重新规划");
        }
    }
}

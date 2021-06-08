package com.bs.weather.util.SuanFa.Aco;



import com.bs.weather.util.SuanFa.Entity.AcoReturn;

public class Aco {
    private final Ant[] ants;           // 蚂蚁数组
    private final int antNum = 50;      // 蚂蚁数量
    private final int cityNum;          // 城市数量
    private final int maxGen = 100;     // 迭代次
    private final float[][] distance;   // 距离矩阵
    private float[][] pheromone;        // 信息素矩阵
    private int bestLength;             // 最佳长度
    private int[] bestTour;             // 最佳路径

    // 四个参数
    private float alpha = 1.f;
    private float beta = 5.f;
    private float rho = 0.5f;
    private float q = 1.f;

    public Aco(float[][] dinstace) {
        cityNum = dinstace.length;
        this.distance = dinstace;
        ants = new Ant[antNum];
        init();
    }

    private void init() {
        // 初始化信息素矩阵
        pheromone = new float[cityNum][cityNum];
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                // 初始化为0.3
                pheromone[i][j] = 0.3f;
            }
        }

        //初始化最佳路径长度
        bestLength = Integer.MAX_VALUE;

        //初始化禁忌表
        bestTour = new int[cityNum + 1];

        // 开始随机放置蚂蚁
        for (int i = 0; i < antNum; i++) {
            ants[i] = new Ant(cityNum, distance, alpha, beta);
        }
    }

    public AcoReturn solve() {

        //死路mark，mark=false则舍弃该条蚂蚁此次路径
        boolean mark = false;

        // 迭代maxGen次
        for (int g = 0; g < maxGen; g++) {
            // 一代迭代
            for (int i = 0; i < antNum; i++) {
                // 每只蚂蚁路线
                for (int j = 1; j < cityNum; j++) {
                    mark = ants[i].selectNextCity(pheromone);
                    if (!mark) {
                        //此处选择break是因为在问题规模较小的时候容易陷入局部最优走死胡同的情况，导致一直循环不能被释放，所以只好放弃该蚂蚁的路径。
                        break;
                    }
                }
                //mark=false所有城市没有走全，那么该条就需要放弃
                if (mark) {
                    //如果tabu内最后一个节点无法到达起点那么就不能形成环，起点就不加入tabu
                    if (distance[ants[i].getTabu().get(ants[i].getTabu().size() - 1)][ants[i].getFirstCity()] != Float.MAX_VALUE) {
                        ants[i].getTabu().add(ants[i].getFirstCity());
                    } else {
                        //不能形成环bestTour的最后一个元素需要变为-1以作为不成环标识位
                        bestTour[bestTour.length - 1] = -1;
                    }
                    // 查看这只蚂蚁行走路径距离是否比当前距离优秀
                    if (ants[i].getTourLength() < bestLength) {
                        // 比当前优秀则拷贝优秀TSP路径
                        bestLength = ants[i].getTourLength();
                        //此处循环条件的判定是根据tabu长度来的，如果形成了换bestTour最后一位将不会是-1
                        for (int k = 0; k < ants[i].getTabu().size(); k++) {
                            bestTour[k] = ants[i].getTabu().get(k);
                        }
                    }
                    // 更新这只蚂蚁的信息素--变化--矩阵，delta
                    for (float[] delta : ants[i].getDelta()) {
                        for (int j = 0; j < delta.length; j++) {
                            delta[j] = q / ants[i].getTourLength();
                        }
                    }
                }
            }

            // 更新信息素
            updatePheromone();

            // 重新初始化蚂蚁
            for (int i = 0; i < antNum; i++) {
                ants[i].init(distance);
            }
        }
        AcoReturn acoReturn = new AcoReturn();
        acoReturn.setBestTour(bestTour);
        acoReturn.setBestLength(bestLength);
        // 返回最佳结果
        return acoReturn;
    }

    // 更新信息素
    private void updatePheromone() {

        // 信息素挥发
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                pheromone[i][j] = pheromone[i][j] * (1 - rho);
            }
        }

        // 信息素更新
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                for (int k = 0; k < antNum; k++) {
                    pheromone[i][j] += ants[k].getDelta()[i][j];
                }
            }
        }
    }
}
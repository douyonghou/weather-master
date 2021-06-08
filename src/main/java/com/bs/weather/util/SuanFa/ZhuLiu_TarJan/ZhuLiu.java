package com.bs.weather.util.SuanFa.ZhuLiu_TarJan;



import com.bs.weather.util.SuanFa.Entity.SetNewMapReturn;

import java.util.*;

public class ZhuLiu {

    private final float INF = Float.MAX_VALUE;
    private float[][] distance;     //距离矩阵
    private int[] minArray;                 //每个节点入边最小的数组
    private int[][] resultArray;            //入边可达性矩阵
    private boolean[] ifInStack;            //判断是否在栈中
    private boolean[] ifVisit;              //判断是否访问过
    private Stack<Integer> stack;           //tarjan栈
    private List<ArrayList> allRingList;    //环总集合
    private int firstPoint;                 //起点
    private int ringCount = 0;              //环数
    private List<float[][]> allDistanceList;//存储每一层递归的distance方便做展开操作时使用
    private List<SetNewMapReturn> allsetNewMapReturnList;//存储每一层递归的SetNewMapReturn方便做展开操作时使用


    //tarjan的三个参数
    int[] dfn;  //时间戳
    int[] low;  //low一致的为同一环
    int index;  //索引值

    public ZhuLiu(float[][] distance, int firstPoint) {
        int l = distance.length;
        this.firstPoint = firstPoint;
        this.distance = distance;
        dfn = new int[l];
        low = new int[l];
        ifInStack = new boolean[l];
        ifVisit = new boolean[l];
        stack = new Stack<>();
        resultArray = new int[l][l];
        allRingList = new ArrayList<>();
        minArray = new int[l];
        allDistanceList = new ArrayList<>();
        allsetNewMapReturnList = new ArrayList<>();
        init();
    }

    public void init() {
        //初始化最短入边矩阵值都为-1
        for (int i = 0; i < minArray.length; i++) {
            minArray[i] = -1;
        }
    }

    //朱刘算法
    public int[] runZhuLiu() {
        //最短距离临时变量
        float minLengthTmp;
        //选取每个节点的最小的入边(朱刘第一步)
        for (int i = 0; i < distance.length; i++) {
            minLengthTmp = Float.MAX_VALUE;
            for (int j = 0; j < distance.length; j++) {
                if (i != j) {
                    if ((minLengthTmp > distance[j][i])) {
                        minLengthTmp = distance[j][i];
                        //diePointList存储入边信息，例如diePointList.get(0)为1则0节点min入边为 1-0
                        minArray[i] = j;
                    } else if (minLengthTmp == distance[j][i] && minArray[i] != -1 && minArray[minArray[i]] == i) {
                        //假设A-B与C-B距离一致，此时A-B并且B-A，那么要用C-B替换A-B以免形成环
                        //diePointList存储入边信息，例如diePointList.get(0)为1则0节点min入边为 1-0
                        minArray[i] = j;
                    }
                }
            }
        }
        //将起点设置为没有最短入边，也就是起点不可达
        minArray[firstPoint] = -1;

        //根据minArray，形成新的可达关系矩阵
        for (int i = 0; i < minArray.length; i++) {
            if (minArray[i] != -1) {
                //当前节点有入边
                //将minArray中可达的距离矩阵坐标至为1
                resultArray[minArray[i]][i] = 1;
            }
            //由于最外层接口做了可达性验证所以不会出现没有入度的节点除了-1
//            else if (i != firstPoint) {
//                //当前节点没有入边&&当前城市不是起点因为起点没有入边
//                //需要完善
//                System.out.println("产生了不可达节点遍历结果集可找出");
//            }
        }


        //如果有环进行缩环为点，如果没有返回结果(缩环为点 朱刘第二步)
        if (ifHaveRing(firstPoint)) {
            //有环进行缩环为点，形成新图(会再次递归朱刘直到没有环)
            SetNewMapReturn setNewMapReturn = shrinkagePoint(distance, firstPoint, allRingList, new SetNewMapReturn());
            //判断新图是否有环
            ZhuLiu zhuliu = new ZhuLiu(setNewMapReturn.getDistanceTmp(), setNewMapReturn.getFirstPointTmp());
            int[] newMinArray = zhuliu.runZhuLiu();
//            if (newMinArray!=null){}  空指针异常处理但是会少点
            return unfoldMap(setNewMapReturn, newMinArray);


        } else {
            //无环构造图形
            //输出---输出没有环直接构造的图形结果
//            System.out.println("无环构造图形");
//            for (int i = 0; i < minArray.length; i++) {
//                if (minArray[i] != -1) {
//                    //起点不可以有入边
//                    System.out.println(minArray[i] + "---->" + i);
//                }
//            }
//            System.out.println();
            //拿到当前传入的distance，此时的distance就是上一层的更新权值后的距离矩阵，根据其及索引关系进行展开
            return minArray;
        }

    }

    //判断是否有环(参数为起点)，tarjan
    public boolean ifHaveRing(int firstPoint) {
        boolean ifHaveRingMark = true;
        //遍历入边可达矩阵，判断是否有环
        for (int i = 0; i < resultArray.length; i++) {
            //将没走过的节点传入，并且当前节点不等于出发点
            if (!ifVisit[i]) {
                //还有节点没走到继续遍历
                recursionHaveRing(i, firstPoint);
            }
        }
        if (ringCount == 0) {
            //无环
            ifHaveRingMark = false;
        }

//        //最短可达距离顺序输出
//        System.out.println("--------------");
//        for (Integer i : resultRingList) {
//            System.out.println(i);
//        }
//        System.out.println("--------------");

        return ifHaveRingMark;
    }

    //判断是否有环递归函数
    public void recursionHaveRing(int u, int firstPoint) {
        //u不等于起始点，因为起始点并没有入边，所以起始点不用加入tarjan，resultArray中起始点入边都为-1
        if (u != firstPoint) {
            //给dfn和low赋值
            dfn[u] = low[u] = index++;
            //当前节点入栈
            stack.push(u);
            //标记当前节点在栈中
            ifInStack[u] = true;
            ifVisit[u] = true;

            //开始遍历其可达节点
            for (int i = 0; i < resultArray.length; i++) {
                //首先保证u-i可以走通
                if (resultArray[u][i] == 1) {
                    //u-i可以走通
                    if (!ifInStack[i]) {
                        //i节点不存在栈中
                        recursionHaveRing(i, firstPoint);
                        //递归完成进行low的更新，如果low[u]<low[i]证明i节点的后续的节点与u节点前面的节点形成了环，所以他们的low都需要更新为该环中最小的low
                        low[u] = Math.min(low[u], low[i]);
                    } else {
                        //如果当前点在栈中
                        //更新low，u节点的low如果小于i节点的dfn那么形成环，更新low[u]
                        low[u] = Math.min(low[u], dfn[i]);
                    }
                }
            }
            if (dfn[u] == low[u]) {
                //是否成环标志
                boolean ringMark = false;
                //环集合
                ArrayList<Integer> ringList = new ArrayList<>();

                //每个节点只有一个入边所以不需要一个节点访问2次的情况（如果出现直接过滤即可）所以是否访问过的矩阵状态不需要更新
                while (stack.size() != 0 && stack.peek() != u) {
                    //如果栈顶元素不等于u则成环
                    ringMark = true;
                    //将是否在栈状态改为false并弹栈
                    ifInStack[stack.peek()] = false;
                    //将该节点存入该环集合
                    ringList.add(stack.pop());
                }
                //即将出栈，标志位恢复为false
                ifInStack[stack.peek()] = false;
                //将是否在栈状态改为false并弹栈(如果未形成环其先驱节点继续向下遍历)
                if (ringMark) {
                    //如果成环则将u也加入ringList，不成环u不加入
                    ringList.add(stack.pop());
                    //成环将ringList加入allringList
                    allRingList.add(ringList);
                    //产生了环，环数和索引值++
                    ringCount++;
                } else {
                    stack.pop();
                }
            }
        }
    }

    //更新权值 && 缩环为点函数
    public SetNewMapReturn shrinkagePoint(float[][] distance, int firstPoint, List<ArrayList> allRingList, SetNewMapReturn setNewMapReturn) {

        //将每一层的结果都存入全局变量中，方便作展开操作时进行取值
        float[][] newDistace = new float[distance.length][distance.length];
        //因为后续distance会变化所以不能直接加入引用需要重新构造新的对象进行加入
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance.length; j++) {
                newDistace[i][j] = distance[i][j];
            }
        }
        allDistanceList.add(newDistace);
        //环中dfn最小的节点,最小节点的后继节点
        int point, point2;
        float[][] distanceTmp = distance;
        //遍历当前环集合,更新和当前环有关的集合
        if (allRingList.size() != 0) {
            ArrayList i = allRingList.get(0);
            //遍历单个环集合
            for (int j = 1; j <= i.size(); j++) {
                //从list最后一个节点(实际是环的第一个节点)开始取
                point = (int) i.get(i.size() - j);
                if ((i.size() - j - 1) < 0) {
                    //point2为该环中最后一个节点需要和第一个节点链接
                    point2 = (int) i.get(i.size() - 1);
                } else {
                    //ponit的后继节点
                    point2 = (int) i.get(i.size() - j - 1);
                }

                //tmp为p-p2的权值
                float tmp = 0;
                //遍历距离矩阵进行边的权值更新
                for (int k = 0; k < distanceTmp.length; k++) {
                    if (tmp == 0) {
                        //保留下准备缩的环内的边的权值因为后续会变化
                        tmp = distanceTmp[point][point2];
                    }
                    if (k != point && k != point2 && distanceTmp[k][point2] != INF && distanceTmp[k][point2] != tmp) {
                        //k不能是环内点并且当前边可以走通不能是INF
                        //并且当前需要更新的边的权值不等于环内点的权值,因为如果等于的情况还更新那么距离将会被更新为0就会出现bug（有的节点会获取不到路径）
                        //更新权值
                        distanceTmp[k][point2] -= tmp;
                    }
                }
            }
        }
        //distanceTmp为更新权值后的距离矩阵
        return setNewMap(distanceTmp, firstPoint, allRingList, setNewMapReturn);
    }

    //构造新图函数
    public SetNewMapReturn setNewMap(float[][] distanceTmp, int firstPointTmp, List<ArrayList> allRingList, SetNewMapReturn setNewMapReturn) {
        //构造新图
        //新图中每个环缩成的点的-----入边距离
        float minNewMapColumn;
        //新图中每个环缩成的点的----出边距离
        ArrayList<Float> minNewMapRow;
        //存储新的距离矩阵的集合
        List<ArrayList> newDistanceList = new ArrayList<>();
        //每一行(除了最后一行)
        ArrayList<Float> distanceRow;
        //存储更新后每个点到环缩成的点的最小权是每个点到环内哪个点的
        //入边存储
        int[] minPointArrayIn = new int[distanceTmp.length];
        //出边存储
        int[] minPointArrayOut = new int[distanceTmp.length];
        //更新后的索引对应数组
        int[] newIndexArray = new int[distanceTmp.length];
        //最后一行
        minNewMapRow = new ArrayList<>();
        for (int i = 0; i < distanceTmp.length; i++) {
            minNewMapRow.add(INF);
        }

        //查看是否有环
        if (allRingList.size() != 0) {
            //如果有环进行缩环为点

            //i为每一个环的集合
            ArrayList i = allRingList.get(0);

            //对minNewMapRow进行初始化
            for (int k = 0; k < minNewMapRow.size(); k++) {
                minNewMapRow.set(k, INF);
            }

            //遍历更新权值后的的距离矩阵
            //遍历x坐标
            for (int j = 0; j < distanceTmp.length; j++) {
                //初始化
                minNewMapColumn = INF;

                //查看j是否在环中
                if (!i.contains(j)) {
                    //j不再环中
                    //初始化
                    distanceRow = new ArrayList<>();
                    //遍历y坐标
                    for (int k = 0; k < minNewMapRow.size(); k++) {
                        if (j != k) {
                            //查看y坐标是否在环中
                            if (i.contains(k)) {
                                if (minNewMapColumn > distanceTmp[j][k]) {
                                    //如果k在环中(取最小值)
                                    //选取新节点的入边
                                    minNewMapColumn = distanceTmp[j][k];
                                    //记录最小值的连接点是环内的哪一个 j-k  k为环内点
                                    minPointArrayIn[j] = k;
                                }
                            } else {
                                //k不在环中(正常加入)
                                distanceRow.add(distanceTmp[j][k]);
                            }
                        } else {
                            //j=k加入0
                            distanceRow.add(0.f);
                        }
                    }
                    //在i的最后一个位置加入j-新节点的距离（j-新节点的最小权值入边）
                    distanceRow.add(minNewMapColumn);
                    //向新的距离矩阵中加入一行
                    newDistanceList.add(distanceRow);
                } else {
                    //如果j在环中那么改行只取值不加入新的距离矩阵 i.add(minNewMapColumn);
                    //如果j在环中(取最小值)
                    //选取新节点的出边
                    //遍历y坐标
                    for (int k = 0; k < minNewMapRow.size(); k++) {
                        if (j != k && !i.contains(k)) {
                            if (minNewMapRow.get(k) > distanceTmp[j][k]) {
                                //j!=k并且k不是环内的点
                                minNewMapRow.set(k, distanceTmp[j][k]);
                                //j-k j为环内点 k-j 0-1 就是0的入边起点为1
                                minPointArrayOut[k] = j;
                            }
                        } else if (i.contains(k)) {
                            //如果j和k都是环内点
                            //此坐标为环内点(u,v) u-v的权值先设置为0，在加入最后一行时进行remove
                            minNewMapRow.set(k, 0.f);
                        }
                    }
                }
                if (j == distanceTmp.length - 1) {
                    //如果j为distanceTmp内最后一个元素那么进行remove操作
                    for (int k = 0; k < minNewMapRow.size(); k++) {
                        if (minNewMapRow.get(k) == 0.f) {
                            //等于0的都为环内的点需要remove
                            minNewMapRow.remove(k);
                            //由于remove操作后集合字符自动向前移动，所以k也需要向前移动，不然会漏掉元素
                            k -= 1;
                        }
                    }
                }
            }
            //最后一个点为新点-新点的权值所以设置为0
            minNewMapRow.add(0.f);
            //向新的距离矩阵中加入一行
            newDistanceList.add(minNewMapRow);
            //至此新的距离矩阵集合构造完毕

            //更新下一个环内编号（因为构成了新的距离矩阵点的序号会更改）
            //对该环节点进行排序(升序排列)
            allRingList.get(0).sort(Comparator.naturalOrder());
            //index为每个列前移的步数
            //如果还有没处理的环
            for (int j = 0; j < distanceTmp.length; j++) {
                //遍历还未处理的环
                int index = 0;
                //count为当前环内的点的加和
                if (allRingList.get(0).contains(j)) {
                    //如果j为环内的点那么
                    newIndexArray[j] = distanceTmp.length - allRingList.get(0).size();
                    continue;
                }
                for (int k = 0; k < allRingList.get(0).size() + 1; k++) {
                    //遍历已处理的环
                    if (k == allRingList.get(0).size()) {
                        //比环内最大的点还要大
                        newIndexArray[j] = j - index;
                        break;
                    } else if ((int) allRingList.get(0).get(k) >= j) {
                        //如果加和大于那么就算出起新的对应索引
                        newIndexArray[j] = j - index;
                        break;
                    }
                    index++;
                }
                if (j == firstPointTmp) {
                    //进行起点索引值更新
                    firstPointTmp = newIndexArray[j];
                }
            }

            //输出--索引对应关系
//            for (int h : newIndexArray) {
//                System.out.println(h);
//            }
//            System.out.println("---------------------------");
            SetNewMapReturn setNewMapReturn1 = new SetNewMapReturn();

            //先进行set操作避免环被删除后在返回
            setNewMapReturn1.setAllRingList(allRingList.get(0));

            //删除处理过的环
            allRingList.remove(0);

            //更新下一个还未处理的环
            if (allRingList.size() != 0) {
                for (int j = 0; j < allRingList.get(0).size(); j++) {
                    allRingList.get(0).set(j, newIndexArray[(int) allRingList.get(0).get(j)]);
                }
            }

            //形成新的距离矩阵（递归使用）
            float[][] distanceTmp1 = new float[newDistanceList.size()][newDistanceList.size()];
            for (int j = 0; j < newDistanceList.size(); j++) {
                for (int k = 0; k < newDistanceList.get(j).size(); k++) {
                    distanceTmp1[j][k] = (float) newDistanceList.get(j).get(k);
                }
            }
            //返回参数构造
            setNewMapReturn1.setDistanceTmp(distanceTmp1);
            setNewMapReturn1.setFirstPointTmp(firstPointTmp);
            setNewMapReturn1.setNewIndexArray(newIndexArray);
            setNewMapReturn1.setMinPointArrayIn(minPointArrayIn);
            setNewMapReturn1.setMinPointArrayOut(minPointArrayOut);
            //存储该层递归的setNewMapReturn
            allsetNewMapReturnList.add(setNewMapReturn1);
            //输---更新权值后的距离矩阵
//            for (float[] o : distanceTmp1) {
//                for (float j : o) {
//                    System.out.printf("%-20s", j + "       ");
//                }
//                System.out.println();
//            }
//            System.out.println("----------------------------------------------------------------------" +
//                    "---------------------------------------------------------------------------------");
            //递归
            return shrinkagePoint(distanceTmp1, firstPointTmp, allRingList, setNewMapReturn1);
        } else {
            return setNewMapReturn;
        }
    }


    public int[] unfoldMap(SetNewMapReturn setNewMapReturn, int[] newMinArray) {
        //展开
        //上一层返回的新的distace
        float[][] returnDistance;
        //上一层返回的新的minarray
        int[] retrunMinArray = null;

        if (allDistanceList.size() > 1) {
            //获取每层的最短入边关系
            //先移除最上层的遗留distacne
            allDistanceList.remove(allDistanceList.size() - 1);
            int i = allDistanceList.size() - 1;
            returnDistance = allDistanceList.get(i);
            retrunMinArray = new int[returnDistance.length];
            //根据distance构造上一层的最短入边数组
            for (int j = 0; j < returnDistance.length; j++) {
                float minLengthTmp = Float.MAX_VALUE;
                for (int k = 0; k < returnDistance.length; k++) {
                    if ((j != k) && (minLengthTmp > returnDistance[k][j])) {
                        minLengthTmp = returnDistance[k][j];
                        //diePointList存储入边信息，例如diePointList.get(0)为1则0节点min入边为 1-0
                        retrunMinArray[j] = k;
                    }
                }
            }
            //将起点设置为没有最短入边，也就是起点不可达
            //在这里起点需要获取和returnMinArray一层的所以需要向上返回一层所以else要-2
            if (allsetNewMapReturnList.size() - 2 == -1) {
                //返回到第二层，由于第一层的firsPoint没有存进集合所以在这里需要判断，不然会数组越界异常
                retrunMinArray[firstPoint] = -1;
            } else {
                //在这里起点需要获取和returnMinArray一层的所以需要向上返回一层所以else要-2
                retrunMinArray[allsetNewMapReturnList.get(allsetNewMapReturnList.size() - 2).getFirstPointTmp()] = -1;
            }
            //newMinArray起点在上一层对应的点
            List<Integer> startPointList = new ArrayList<>();
            //newMinArray终点在上一层对应的点
            List<Integer> endPointList = new ArrayList<>();
            for (int j = 0; j < newMinArray.length; j++) {
                //遍历索引关系
                if (j != setNewMapReturn.getFirstPointTmp()) {
                    //j终点不可以为起点
                    //终点在环内
                    //初始化
                    startPointList.clear();
                    endPointList.clear();
                    //遍历索引关系找出起点对应的索引值
                    for (int k = 0; k < setNewMapReturn.getNewIndexArray().length; k++) {
                        //遍历索引集
                        if (setNewMapReturn.getNewIndexArray()[k] == newMinArray[j]) {
                            //找到起点多个索引，加入集合
                            startPointList.add(k);
                        } else if (setNewMapReturn.getNewIndexArray()[k] == j) {
                            //找到终点多个索引加入集合
                            endPointList.add(k);
                        }
                    }
                    //到此处对应的索引都拿到了，进行展开
                    int startPoint = -1;
                    int endPoint = -1;
                    //由于每次只缩一个环所以minRingReturn和minPointReturn必然有一个size为1
                    if (startPointList.size() == 1 && endPointList.size() == 1) {
                        //如果两个点都不是上层环内点那么不做操作
                        //获取起点
                        startPoint = startPointList.get(0);
                        //获取终点
                        endPoint = endPointList.get(0);
                        retrunMinArray[endPoint] = startPoint;
                        continue;
                    } else if (startPointList.size() == 1) {
                        //终点为环缩成的点
                        //获取起点
                        startPoint = startPointList.get(0);
                        //获取终点
                        endPoint = setNewMapReturn.getMinPointArrayIn()[startPoint];
                    } else if (endPointList.size() == 1) {
                        //起点为环缩成的点
                        //获取终点
                        endPoint = endPointList.get(0);
                        //获取起点
                        startPoint = setNewMapReturn.getMinPointArrayOut()[endPoint];
                    }
                    //更新minArray，展开操作
                    retrunMinArray[endPoint] = startPoint;
                }
            }
            //输出---每次展开的结果
//            for (int j = 0; j < retrunMinArray.length; j++) {
//                if (retrunMinArray[j] != -1) {
//                    //起点不可以有入边
//                    System.out.print("V");
//                    System.out.print(retrunMinArray[j] + 1 + "---->");
//                    System.out.print("V");
//                    System.out.println(j + 1);
//                }
//            }
//            System.out.println("---------------------------------------------------");
            //参数封装
            allsetNewMapReturnList.remove(allsetNewMapReturnList.size() - 1);
            //递归按层展开
            if (allsetNewMapReturnList.size() > 0) {
                return unfoldMap(allsetNewMapReturnList.get(allsetNewMapReturnList.size() - 1), retrunMinArray);
            }
        }
        return retrunMinArray;
    }
}

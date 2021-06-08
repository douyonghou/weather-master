package test;

import java.util.Random;

/**
 * @ClassName : MyRandom
 * @Description : 随机数
 * @Author : douyonghou
 * @Date: 2021-06-04 10:49
 */
public class MyRandom {
    public static void main(String[] args) {

        for(int i = 0 ; i < 20; i++ ){
            int max=2,min=0;
            int ran2 = (int) (new Random().nextInt((int)Math.ceil((double)20 / 6)));
            int ran = (int) (new Random().nextInt(6));
            System.out.println(ran);
        }

    }
}

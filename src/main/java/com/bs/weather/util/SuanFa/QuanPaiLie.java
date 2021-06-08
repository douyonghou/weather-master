package com.bs.weather.util.SuanFa;

public class QuanPaiLie {
    static String[] data = {"北京", "上海", "杭州","杭州"};
    static int len = data.length;

    public static void main(String[] args) throws Exception {
        permutation(data, 0);
    }

    public static void permutation(String[] data, int index) {
        if (index == (len - 1)) {
            for (String temp : data) {
                System.out.print(temp + " ");
            }
            System.out.println();
        } else {
            for (int i = index; i < len; i++) {
                if (swapAccepted(data, index, i)) {
                    continue;
                }
                swap(data, index, i);
                permutation(data, index + 1);
                swap(data, index, i);
            }
        }
    }

    public static void swap(String[] data, int i, int j) {
        String temp = data[i];
        data[i] = data[j];
        data[j] = temp;

    }

    private static boolean swapAccepted(String[] data, int start, int end) {
        for (int i = start; i < end; i++) {
            if (data[i] == data[end]) {
                return true;
            }
        }
        return false;
    }
}

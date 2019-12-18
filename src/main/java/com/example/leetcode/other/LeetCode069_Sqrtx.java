package com.example.leetcode.other;

import org.junit.Test;

/**
 * @description: x的平方根
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 * @auther: icecrea
 * @date: 2019/12/18
 */
public class LeetCode069_Sqrtx {

    /**
     * 思路：二分查找
     * 根据习惯我们推测，一个数的平方根一般不超过它的一般 求解这 a <= (a/2)^2 得 a>=4或a<=0
     * 所以0123这四个属于边界值，需要特别注意。他们开根号分别为0111。
     */
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int start = 1;
        int end = x / 2;
        int mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
            //防止mid * mid越界,使用除法
            if (mid <= (x / mid) && (mid + 1) > x / (mid + 1)) {
                return mid;
            }
            if (mid > x / mid) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return mid;
    }

    @Test
    public void test() {
        System.out.println(mySqrt(0));
        System.out.println(mySqrt(1));
        System.out.println(mySqrt(2));
        System.out.println(mySqrt(3));
        System.out.println(mySqrt(8));
    }
}

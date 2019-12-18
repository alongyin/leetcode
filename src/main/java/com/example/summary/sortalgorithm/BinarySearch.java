package com.example.summary.sortalgorithm;

import com.example.leetcode.binarysearch.LeetCode33_SearchInRotatedSortedArray;
import com.example.leetcode.other.LeetCode069_Sqrtx;
import com.example.topinterview.easy.sort.array53_firstBadVersion;
import org.junit.Test;

/**
 * @description: 二分查找
 * 二分查找实现
 * 有重复元素存在下，四个变形问题
 * 1.查找第一个等于给定值的元素
 * 2.查找最后一个值等于给定值的元素
 * 3.查找第一个大于等于给定值的元素
 * 4.查找最后一个小于等于给定值的元素
 * 相关题目：
 * 1.循环有序数组何如二分查找？
 * @see LeetCode33_SearchInRotatedSortedArray
 * 2.求一个数的平方根，要求精确到小数点后6位
 * @see LeetCode069_Sqrtx
 * 3.第一个错误版本
 * @see array53_firstBadVersion
 * @author: icecrea
 * @create: 2019-10-24
 **/
public class BinarySearch {

    /**
     * 最简单情况：有序数组中不存在重复元素
     * 时间复杂度O(logn)
     * 注意 1.循环退出条件大于等于 low<=high 2.low=mid+1，high=mid-1。如果写成low=mid/hight=mid，可能发生死循环。
     *
     * @param a
     * @param n     数组个数
     * @param value 查找值
     * @return
     */
    public int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 二分查找的递归实现
     */
    public int bsearchRecur(int[] a, int n, int val) {
        return bsearchInternally(a, 0, n - 1, val);
    }

    private int bsearchInternally(int[] a, int low, int high, int value) {
        if (low > high) {
            return -1;
        }
        int mid = low + ((high - low) >> 1);
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bsearchInternally(a, mid + 1, high, value);
        } else {
            return bsearchInternally(a, low, mid - 1, value);
        }
    }


    /**
     * 变形一：
     * 存在重复元素，查找第一个等于给定值的元素
     */
    public int bsearch1(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                //此处区别在于，当a[mid]等于需要的值时，还需要确认是不是第一个值等于给定值的元素.当遍历到数组第一个数，或者左边值不同时，必定是第一个，直接返回
                if ((mid == 0) || (a[mid - 1] != value)) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }


    /**
     * 变形二：
     * 查找最后一个值等于给定值的元素
     */
    public int bsearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                //此处区别在于，当a[mid]等于需要的值时，还需要确认是不是第一个值等于给定值的元素.当遍历到数组第一个数，或者左边值不同时，必定是第一个，直接返回
                if ((mid == n - 1) || (a[mid + 1] != value)) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }


    /**
     * 变形三：
     * 查找第一个大于等于给定值的元素
     * 如序列：3，4，6，7，19 查找第一个大于5的元素，即为6
     * 第一个大于给定值，则说明上一个小于给定值，依次判断
     */
    public int bsearch3(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 变形四：
     * 查找最后一个小于等于给定值的元素、
     * 如：3，5，6，8，9，10 最后一个小于7的元素是6
     */
    public int bsearch4(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] > value)) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int a[] = new int[]{-1, 1, 2, 3, 4, 5, 10};
        System.out.println(bsearch3(a, a.length, 3));
    }

}

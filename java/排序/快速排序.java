package cn.aq.排序;

import javax.xml.crypto.Data;

/**是对冒泡排序的一种改进。基本思想是：
 * 1．先从数列中取出一个数作为基准数
 * 2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边
 * 3．再对左右区间重复第二步，直到各区间只有一个数*/

//快速排序
public class 快速排序 {
    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-567,70};
        quickSort(arr,0,5);
        for (int s:arr)
            System.out.print(s+" ");
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int l = left;
            int r = right;
            int temp = arr[left];   //临时挖坑
            while (l < r) {
//                从右边开始寻找是否有一个数比 基准数 小，如果找到就将这个数挖出填入坑中
                while (l < r && arr[r] >= temp)
                    r--;
                arr[l] = arr[r];
//                此时一开始挖的坑虽然填上了，但是 arr[right]又出现了新坑
//                于是从左边开始找一个比 基准数 大的数填入坑中
                while (l < r && arr[l] <= temp)
                    l++;
                arr[r] = arr[l];
            }
//            跳出循环时，right 和 left 相等，将基准数填入这个最后的坑中
            arr[l] = temp;
//            将数据从 temp 一分为二，继续进行 quickSort
            quickSort(arr, left, l - 1);
            quickSort(arr, l + 1, right);
        }
    }

}

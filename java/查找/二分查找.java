package cn.aq.查找;

/*二分查找思路分析
* 1、首先确定该数组（有序数组）的中间下标 mid = (left + right)/2
* 2、然后让需要查找的数 findVal 和 arr[mid]比较
*       findVal >|<|= arr[mid]，递归向右/向左
* 3、找到/递归完整个数组，需要结束递归*/
public class 二分查找 {
    public static void main(String[] args) {
        int arr[] = {1,8,10,89,1000,1234};
        System.out.println(binartSearch(arr,0,arr.length-1,8));
    }

    public static int binartSearch(int[] arr, int left, int right, int value) {
//        当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right)
            return -1;
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (value > midVal)
            return binartSearch(arr,mid + 1,right,value);
        else if (value < midVal)
            return binartSearch(arr,left,mid-1,value);
        else
            return mid;
    }
}

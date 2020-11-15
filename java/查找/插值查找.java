package cn.aq.查找;


/*插值查找原理介绍：
* mid = left + (right - left) * (key - arr[left]) / (arr[right] - arr[left])
* key = 要查找的值*/
public class 插值查找 {
    public static void main(String[] args) {
        int arr[] = {1,8,10,89,1000,1234};
        System.out.println(insertValueSearch(arr,0,arr.length-1,1000));
    }

    public static int insertValueSearch(int[] arr, int left, int right, int value) {
        //        当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right || value > arr[arr.length-1] || value < arr[0])
            return -1;
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (value > midVal)
            return insertValueSearch(arr,mid + 1,right,value);
        else if (value < midVal)
            return insertValueSearch(arr,left,mid-1,value);
        else
            return mid;
    }
}

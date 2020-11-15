package cn.aq.排序;

/**基本思想：
 * 给定一个整数数组，给定一个值k，这个值在原数组中一定存在。
 * 要求把数组中小于 k 的元素放到数组的左边，大于 k 的元素放到数组的右边，等于 k 的元素放到数组的中间
 * 最终返回一个整数数组，其中只有两个值，分别是等于 k 的数组区域左右两个下标值*/
public class 荷兰国旗问题 {

    public static int[] arr = {7,9,2,1,5,5,4,3};

    public static void main(String[] args) {
        quicksort(arr,0,7);
        for (int arr: arr) {
            System.out.print(arr);
        }
    }

//    快速排序
    public static void quicksort(int[] arr, int left, int right) {
        if (left < right) {
            int[] temp = partition(arr, left,right,arr[(left + right) / 2]);
            quicksort(arr,left,temp[0]);
            quicksort(arr,temp[1],right);
        }
    }

    /** )[]( = L left right R
     * @param left：基准区域的左边界
     * @param right：基准区域的右边界
     * L：小数据区的右边界，且也是遍历的当前的数
     * R：大数据区的左边界*/
    public static int[] partition(int[] arr,int left,int right,int BenchmarkData) {
        int L = left - 1;
        int R = right + 1;
        while (left < R) {
            if (arr[left] < BenchmarkData)
                swapData(arr,++L,left++);
            else if (arr[left] > BenchmarkData)
                swapData(arr,--R,left);
            else
                left++;
        }
        return new int[]{L++,R--};
    }
    /*交换数据位置*/
    public static void swapData(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

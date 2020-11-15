package cn.aq.排序;

/**基本思想：
 * */
public class 归并排序 {
    public static void main(String[] args) {
        int arr[] = { 8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length - 1,temp);
        for (int i : arr)
            System.out.print(i+" ");
    }

//    分 + 合 方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
//            向左递归进行分解
            mergeSort(arr, left, mid, temp);
//            向右递归进行分解
            mergeSort(arr,mid + 1,right, temp);
//            到合并
            merge(arr, left, right, mid, temp);
        }
    }

//    合并的方法
/**
 * @Param arr 排序的原始数组
 * @Param left 左边有序序列的初始索引
 * @Param right 右边有序序列的初始索引
 * @Param mid 中间索引
 * @Param temp 临时数组
* */
    public static void merge(int[] arr, int left, int right, int mid, int[] temp) {
        int i = left;       //初始化 i，左边有序序列的初始索引
        int j = mid + 1;    //初始化 j，右边有序序列的初始索引
        int t = 0;          //指向 temp 数组的当前索引

//        1、先把左右两边的数据（有序）的数据按照规则填充到 temp 数组
//        2、直到左右两边的有序序列，有一边处理完为止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
//        3、把有剩余的数据的一遍的数据一次全部填充到 temp
        while ( i <= mid) { //说明左边有序序列还有剩余元素
            temp[t] = arr[i];
            t ++;
            i ++;
        }
        while ( j <= right) { //说明右边有序序列还有剩余元素
            temp[t] = arr[j];
            t ++;
            j ++;
        }
//        4、将 temp 数组的元素拷贝到 arr
//        5、注意：并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

    }

}

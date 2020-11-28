package cn.aq.排序;


/**1、基数排序（radix sort）属于“分配式排序（distribution sort），又称“桶子法（bucket sort）或 bin sort
 * 顾名思义，它是通过键值的各个位的值，将要排序的元素分配至某些“桶”中，达到排序的作用
 * 2、基数排序法是属于稳定性的排序，基数排序法的是效率高的稳定性排序法
 * 3、基数排序是桶排序的扩展
 * 4、基数排序是 1887 年 赫尔曼·何乐礼 发明的。它是这样实现的：将证书按位切割成不同的数字，然后按照每个位数分别比较
 *
 * 基本思想：
 * 1、将所有待比较数值统一为同样的数位长度，数位较短的数前面补0，然后从最低位开始，依次进行一次排序。
 * 2、这样从最低位排序一直到最高位排序完成以后，数列就变成一个有序序列
 *
 * 基数排序的说明
 * 1、基数排序是对传统 桶排序 的扩展，速度很快
 * 2、基数排序是经典的空间换时间的方式，占用内存很大，当对海量数据排序时，容易造成 OutOfMemoryError
 * 3、基数排序是稳定的*/
public class 基数排序 {
    public static void main(String[] args) {
        int arr[] = {53,3,542,748,14,214};
        radixSort(arr);
        for (int i:arr)
            System.out.println(i);
    }

//    基数排序方法
    public static void radixSort(int[] arr) {
//        定义 10 个桶，用一个二维数组表示
//        为了防止在放数的时候溢出，则每一个一维数组的长度为 arr.length
        int[][] bucket = new int[10][arr.length];
//        为了记录每个桶实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[10];

//        找到数组中最大数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];
//        通过字符串长度间接得到 Length，表示循环次数
        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < 3; i++,n*=10) {
//            针对每个元素的对应位进行排序处理，第一次是个位，第二次是十位，第三次是百位，n代表位置
            for (int j = 0; j < arr.length; j++) {
//                取出每个元素的个位数的值
                int digitOfElement = arr[j] / n % 10;
//                放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
//            按照这个桶的顺序（一维数组的下标依次取出数据，放入到原来数据）
            int index = 0;
            for (int k = 0;k < bucketElementCounts.length; k++) {
//                如果桶中有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
//                    循环第 k 个桶（即第 k 个一维数组）
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
//                        取出元素放入到 arr
                        arr[index++] = bucket[k][l];
                    }
                }
//                第 i 轮处理后，需要将每个 bucketElementCounts[k] = 0
                bucketElementCounts[k] = 0;
            }
        }
    }
}

package cn.aq;



//排序算法
public class L_选择_插入_希尔排序 {

    /*选择排序*/
/**第一次从 arr[0]-arr[n-1] 中选取最小值，与 arr[0] 交换
 * * 第二次从 arr[1]-arr[n-1] 中选取最小值，与 arr[1] 交换，以此类推*/
    public static void selectSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i ++)
            for (int j = i+1; j < arr.length; j ++)
                if (arr[i] < arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
    }

    /*插入排序*/
/**把 n 个待排序的元素看成为一个有序表和一个无序表
 * *
 * 开始时，有序表中只包含一个元素，把它的排序码依次与有序表元素的排序码比较，将它插入到有序表的适当位置，使之成为新的有序表
 * 缺点：当需要插入的数是较小的数时，后移的次数明显增多，对效率有影响*/
    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i ++) {
            int insertVal = arr[i];     //定义待插入的数
            int insertIndex = i - 1;
            //将插入位置的数的后面的数向后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出 while 循环时，说明插入的位置找到，insertIndex + 1
            if (insertIndex + 1 != i)
                arr[insertIndex + 1] = insertVal;
            for (int s:arr)
                System.out.print(s);
            System.out.println();
        }
    }

    /*希尔排序*/
/**【交换式】希尔排序
 * 希尔排序是希尔于 1959 年提出的一种排序算法
 * 希尔排序也是一种 插入排序！它是简单插入排序经过改进之后的一个更高的版本，也称为缩小增量排序
 * 希尔排序是把记录按下标的一定增量分组（比如10个数，按05,16,27,38,49分组，分成五组）
 * 对每组使用直接插入排序算法排序，如0和5比较，如果0比5小，则交换位置
 * 这是第一轮排序，被分成了 5 组，第二轮分：5/2 = 2 组
 * 第三轮：2/2 = 1 组
 * 对于每组进行的方法是 交换排序，冒泡
 * 最后使用简单插入排序*/
    public static void shellSort1(int[] arr) {
        int temp = 0;
        for (int step = arr.length / 2; step > 0; step /= 2)    //每一次的分组除以2
            for (int i = step; i < arr.length; i++)
                for (int j = i - step; j >= 0; j -= step)
                    if (arr[j] > arr[j + step]) {
                        temp = arr[j];
                        arr[j] = arr[j + step];
                        arr[j + step] = temp;
                    }
    }

/**改进：【移位式】希尔排序
 * */
    public static void shellSort2(int[] arr) {

        for (int step = arr.length / 2; step > 0; step /= 2) {
            //从第 step 个元素，逐个对其所在的组进行直接插入排序
            for (int i = step; i < arr.length; i++) {
                //传统插入排序
                int j = i;
                int temp = arr[j];
                while (j - step >= 0 && temp < arr[j - step]) {
                    arr[j] = arr[j - step];
                    j -= step;
                }
                //当退出 while 后，就给 temp 找到插入的位置
                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int []arr = {3,4,1,2,5};
        shellSort2(arr);
        for (int s:arr)
            System.out.print(s);

    }
}

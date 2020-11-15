package cn.aq.排序;

/**基本思想：
 * 堆结构其实就是一颗完全二叉树（抽象）
 * 1、建立大根堆（heap insert）：
 *      如果子节点比父节点大，就交换位置，并子节点来到父节点的位置，继续比较。
 *      如果跳到 arr【0】，就 arr【0】和 arr【(0-1)/2】比较，相等，退出
 * 2、把大根堆最后一个位置和头部交换，缩小堆范围，然后继续排大根堆
 *
 *
 * 好处：我们添加一个数，弹出一个数，只要 logN 的复杂度（代价只为层数）
 *      搞定贪心算法*/
public class 堆排序 {

    public static int[] arr = {7,9,2,1,5,5,4,3};

    public static void main(String[] args) {
        heapSort(arr);
        for (int arr : arr)
            System.out.print(arr);

    }

    /*堆排序*/
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;
        for (int i = 0 ; i < arr.length ; i++)
            heapInsert(arr,i);
        int heapSize = arr.length;
        swapData(arr,0,--heapSize); //最后一个数和 0 位置数交换
        while (heapSize > 0) {
            heapify(arr,0,heapSize);
            swapData(arr,0,--heapSize);
        }
    }

    /**数据与父节点交换并上移*/
    public static void heapInsert(int[] arr,int index) {
        while (arr[index] > arr[(index - 1)/2]) {
            swapData(arr,index,(index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**数据往下沉的操作，在向下沉的时候，哪个孩子大就跟哪个孩子交换
     * @param index 当前数据的位置
     * @param heapSize 数组长度*/
    public static void heapify(int[] arr,int index,int heapSize) {
        int left = 2 * index + 1;   //右孩子
        while (left < heapSize) {
//            比较左右孩子大小
            int largest = (left + 1 < heapSize && arr[left + 1] > arr[left]) ? left + 1 : left;
//            比较父亲与被比较节点的位置
            largest = arr[left] > arr[index] ? largest : index;
            if (largest == index)
                break;
//            交换位置
            swapData(arr,largest,index);
            left = 2 * index + 1;   //继续向下寻找
        }
    }

    /*交换数据位置*/
    public static void swapData(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

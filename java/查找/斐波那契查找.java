package cn.aq.查找;

import java.util.Arrays;

/*斐波那契（黄金分割法）原理
* */
public class 斐波那契查找 {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int arr[] = {1,8,10,89,1000,1234};
    }

//    因为后面我们 mid = low + F(k-1)-1，需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
//    非递归方法得到一个斐波那契数列
    public static int[] fibonacci() {
        int[] F = new int[maxSize];
        F[0] = 1;
        F[1] = 1;
        for (int i = 2; i< maxSize; i++)
            F[i] = F[i-1] + F[i-2];
        return F;
    }
//    填写斐波那契查找算法
//    使用非递归方式编写算法
    public static int fibonacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;  //表示 斐波那契分割数值的下标
        int mid = 0;
        int[] f = fibonacci();      //获取斐波那契数列
//        获取到斐波那契分割数值的下标，也就是找到 high 在 fibonacci 中的位置
        while (high > f[k] - 1)
            k++;
//        因为 f[k] 的值可能大于数组的长度，因此我们需要使用 Arrays类，构造一个新的数组，并指向temp[]
//        不足的部分会使用 0 填充
        int[] temp = Arrays.copyOf(arr,f[k]-1);
//        实际上需要使用 arr 数组最后的数填充 temp
        for (int i = high + 1;i < temp.length; i++)
            temp[i] = arr[high];

//        使用 while 来循环处理，找到我们的数 key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {  //成立说明，我们应该继续向数组的前面查找（左边）
                high = mid - 1;
//                说明为什么是 k--：
//                全部元素 = 前面的元素 + 后面的元素
//                f[k] = f[k - 1] + f[k - 2]
//                因为前面有 f[k-1] 个元素，所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
//                即在 f[k-1] 的前面继续查找 k--
//                即下次循环 mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) {   //向后方查找
                low = mid + 1;
//                说明为什么是 k-=2：
//                全部元素 = 前面的元素 + 后面的元素
//                f[k] = f[k - 1] + f[k - 2]
//                因为后面我们有 f[k-2]，所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
//                即在 f[k-2] 的前面进行查找 k -= 2
//                即下次循环 mid = f[k-1-2]-1
                k -= 2;
            } else {    //找到
                if (mid <= high)
                    return mid;
                else
                    return high;
            }
        }
        return -1;
    }
}

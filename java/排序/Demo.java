package cn.aq.排序;


/*测试类*/
public class Demo  {

    static int[] arr = new int[30];

    public static void main(String[] args) {
        Recursion(0,0);

    }

    public static int Recursion(int i,int count) {
        int sum = 0;
        if(count < 10) {
            if (i == 0 || i == 1) {
                arr[i] = 1;
                System.out.println(arr[i]);
                return Recursion(i + 1, count + 1);
            }
            arr[i] = arr[i - 1] + arr[i - 2];
            System.out.println(arr[i]);
            return Recursion(i + 1, count + 1);
        }
        return arr[i];
    }
}






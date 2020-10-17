package cn.aq;

import java.io.IOException;
import java.util.Scanner;

//数组模拟队列
public class B_CircleArrayQueue {
    public static void main(String[] args) {

        //创建一个队列，创建长度为4，实际只能存3
        CircleArrayQueue queue = new CircleArrayQueue(5);

        int key = -1;   //用户输入控制

        Scanner in = new Scanner(System.in);

        boolean loop = true;
        while (loop) {
            System.out.println("1、显示队列");
            System.out.println("2、添加数据");
            System.out.println("3、移除数据");
            System.out.println("4、查看队头");
            System.out.println("5、退出程序");
            key = in.nextInt();
            switch (key) {
                case 1:
                    queue.showQueue();
                    break;
                case 2:
                    System.out.println("输出一个数");
                    int value = in.nextInt();
                    queue.addQueue(value);
                    break;
                case 3:
                    try {
                        int res = queue.delQueue();
                        System.out.println("已删除元素"+res);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    int res = queue.headQueue();
                    System.out.println(res);
                    break;
                case 5:
                    loop=false;
                    in.close();
                    break;
            }
        }

    }
}

//使用数组模拟队列编写一个 ArrayQueue 类
class CircleArrayQueue {
    private int MAX;    //定义数组的最大容量
    private int front;  //队列头
    private int rear;   //队列尾的下一个元素
    private int[] arr;  //该数组用于存放数据，模拟队列

//    1、创建队列的构造方法
    public CircleArrayQueue(int MAX) {
        this.MAX = MAX;     //规定最大值
        arr = new int[MAX]; //初始化
        front = rear = 0;  //队头和队尾，内存中初始化为
    }

//    2、判断队列是否满
    public boolean isFull() {
        return (rear+1)%MAX == MAX; //rear指向最大值
    }

//    3、判断队列是否空
    public boolean isEmpty() {
        return rear == front;
    }

//    4、添加数据到队列
    public void addQueue(int n){
        if (isFull()){   //判断队列是否满
            System.out.println("队列满，不能加入数据！");
            return;
        }
//        直接将数据加入
        arr[rear] = n;
//        将 rear 后移
        rear = (rear+1)%MAX;
    }

//    5、出队列，并获取到出队的值
//    此方法实际上并没有取出，只是指针向前移动而已
    public int delQueue() {
        if (isEmpty())
            throw  new RuntimeException("队列空，不能删数据");
        /**
         * 这里需要分析出 front 是指向队列的第一个元素
         * 1、先把 front 对应的值保留到一个临时变量表
         * 2、将 front 后移，考虑取模
         * 3、将临时保存的变量返回*/
        int value = arr[front];
        front = (front + 1)%MAX;
        return value;
    }

//    6、遍历队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front ; i < front + size(); i++)
            System.out.print(arr[i%MAX]);
        System.out.println();


    }
//    7、求出当前队列有效数据的个数
    public int size() {
        return (rear + MAX - front) % MAX;
    }

//    8、显示队列的头数据
    public int headQueue() {
        if (isEmpty())
            throw  new RuntimeException("队列空，不能取数据");
        return arr[front];
    }
}

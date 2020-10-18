package cn.aq;

//数组实现栈
//如果是链栈，就是砍掉一些功能的链表
public class F_数组栈 {
    public static void main(String[] args) {

        ArrayStack arrayStack = new ArrayStack(5);
        DoArrayList doArrayList = new DoArrayList();

        doArrayList.IntArrayStack(1);
        doArrayList.IntArrayStack(2);
        doArrayList.IntArrayStack(3);
        doArrayList.OutArrayStack();
        doArrayList.ShowArrayStack();

    }
}

class ArrayStack {
    public static int MAX;        //存储最大容量
    public static int[] data;
    public static int top = -1;        //top为栈顶

    ArrayStack(int MAX){
        this.MAX =MAX;
        data = new int[this.MAX];   //初始化数组
    }
}

class DoArrayList {
    /*判断满*/
    public boolean isFull() {
        return ArrayStack.MAX - 1 == ArrayStack.top;
    }

    /*判断空*/
    public boolean isEmpty() {
        return ArrayStack.top == -1;
    }

    /*入栈*/
    public void IntArrayStack(int value) {
        if (isFull())
            return;
        ArrayStack.top++;
        ArrayStack.data[ArrayStack.top] = value;
    }

    /*出栈*/
    public void OutArrayStack() {
        if (isEmpty())
            return;
        ArrayStack.top--;
    }

    /*遍历*/
    public void ShowArrayStack() {
        if (isEmpty())
            return;
        for (int i = ArrayStack.top; i >= 0; i--)
            System.out.println("stack= "+ ArrayStack.data[i]);
    }
}


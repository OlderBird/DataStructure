package cn.aq;

/*使用栈完成表达式的计算思路
*   定义两个栈：
*       数栈 numStack：存放数
*       符号栈 operStack：存放运算符
*   1、通过一个 index 值（索引），来遍历我们的表达式
*   2、如果我们发现稻扫描到的是一个数字，就直接入数栈
*   3、如果我们发现扫描到的是一个符号，就分如下情况：
*       1）如果发现当前的符号栈为空，就直接入栈
*       2）如果发现符号栈有操作符，就进行比较
*           （1）如果当前的操作符优先级 小于或等于 栈中的操作符
*               ① 就需要从数栈中 pop 出两个数，在从符号栈 pop 出一个符号，进行运算，将得到的结果入数栈
*               ② 将当前的操作符入符号栈
*           （2）如果当前的操作符优先级 大于 栈中的操作符
*               ① 直接入符号栈，不进行计算
*   4、当表达式扫描完毕之后，就顺序的从 数栈 和 符号栈 中pop出相应的数和符号，并运行
*   5、最后在数栈只有一个数字，就是表达式的结果*/

//栈实现计算器
public class G_栈实现计算器 {
    public static void main(String[] args) {

        String expression = "1-2+3-4+5-4-4";

//        创建两个栈，一个数栈，一个符号栈
        ArrayStack_cal numStack = new ArrayStack_cal(10);
        ArrayStack_cal operStack = new ArrayStack_cal(10);
//        定义需要的相关变量
        int index = 0;  //用于扫描字符串
        int num1 = 0;   //操作数1
        int num2 = 0;   //操作数2
        int oper = 0;   //运算符
        int result = 0; //计算结果
        char ch = '0';   //将每次扫描得到的 char 保存到 ch 中
        String temp = "";   //定义临时字符串，用于拼接多位数的情况

//        用 while 循环遍历 expression
        while (true) {
//            依次得到 expression 的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
//            判断 ch 是什么，然后做相应的处理
//            如果是运算符
            if (operStack.IsOper(ch)) {
//                判断当前符号栈是否为空
                if (operStack.IsEmpty())
                    operStack.Push(ch); //如果为空直接入栈
                else {
//                    判断优先级
//                    如果 ch 优先级小于 栈中优先级，则从数栈中 pop 出两个数，从符号栈 pop 出一个数进行运算
//                    在此处，即使是栈中是“-”，要入“+”，也会先进行前者的计算，所以是对的
                    if (operStack.Priority(ch) <= operStack.Priority(operStack.ReturnTop())) {
                        num1 = numStack.Pop();
                        num2 = numStack.Pop();
                        oper = operStack.Pop();
                        result = numStack.Calculate(num1, num2, oper);
                        numStack.Push(result);
//                        然后将当前的操作数入符号栈
                        operStack.Push(ch);
                    }
//                    栈中运算符优先级等于ch，直接入栈
                    else
                        operStack.Push(ch);
                }


            } else {
//                如果是数，则直接入栈
//                分析思路：
//                    1、处理多位数，如果数的前一位也是数，就进行拼接，非数才入栈
//                因此我们定义一个字符串用于拼接
                temp += ch;
//                如果 ch 已经是 experssion 的最后一位，就直接入栈
                if (index == expression.length() - 1)
                    numStack.Push(Integer.parseInt(temp));
                else {
//                    判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    if (operStack.IsOper(expression.substring(index + 1, index + 2).charAt(0))) {
//                        如果最后一位是运算符，则入栈 temp
                        numStack.Push(Integer.parseInt(temp));
                        temp = "";  //temp清空
                    }
                }
            }
            index++;
            if (index >= expression.length())
                break;
        }

//        当表达式扫描完毕，就顺序的从数栈和符号栈中 pop 出相应的数和符号，并运行
        while (true) {
//            如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
            if (operStack.IsEmpty())
                break;
            num1 = numStack.Pop();
            num2 = numStack.Pop();
            oper = operStack.Pop();
            result = numStack.Calculate(num1, num2, oper);
            numStack.Push(result);
        }
//        输出结果，将 数栈 最后的数 pop 出
        result = numStack.Pop();
        System.out.println(result);



    }
}

/*定义一个栈，用于存放数或符号*/
class ArrayStack_cal {
    private int length;
    private int[] data;
    private int top = -1;

    ArrayStack_cal(int length) {
        this.length = length;
        data = new int[this.length];
    }

    public int[] getData() { return data; }

    public void setData(int[] data) { this.data = data; }

    public int getTop() { return top; }

    public void setTop(int top) { this.top = top; }

    /*定义一个方法，返回当前栈的栈顶*/
    public int ReturnTop() {
        return  data[top];
    }

    /*判断栈满*/
    public boolean IsFull() {
        return top == length-1;
    }

    /*判断栈空*/
    public boolean IsEmpty() {
        return top == -1;
    }

    /*入栈*/
    public void Push(int value) {
        if (IsFull())
            return;
        top++;
        data[top] = value;
    }

    /*出栈*/
    public int Pop() {
        int value;
        if (IsEmpty())
            throw new RuntimeException("栈空！没有数据");
        value = data[top];
        top--;
        return value;
    }

    /*遍历栈*/
    public void ShowStack() {
        if (IsEmpty()) {
            System.out.println("栈空！");
            return;
        }
        for (int i = top; i >=0; i--)
            System.out.print(data[i]);
        System.out.println("");
    }

    /*返回符号的优先级*/
    public int Priority(int oper) {
        if (oper == '*' || oper == '/')
            return 1;
        else if (oper == '+' || oper == '-')
            return 0;
        else
            return -1;
    }

    /*判断是不是一个运算符*/
    public boolean IsOper(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    /*计算方法，num1 是栈顶的数，num2 是栈顶下面的数*/
    public int Calculate(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2/num1;
                break;
            default:
                break;
        }
        return result;
    }
}

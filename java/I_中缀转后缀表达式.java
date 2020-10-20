package cn.aq;

import java.util.ArrayList;
import java.util.List;

/**中缀表达式转后缀表达式的思路步骤分析
 * 1）初始化两个栈，运算符 s1 和储存中间结果的栈 s2；
 * 2）从左至右扫描中缀表达式
 * 3）遇到操作数，将其压 s2；
 * 4）遇到运算符时，比较其与 s1 栈顶运算符的优先级
 *      1、如果 s1 为空，或栈顶运算符为左括号“（”，则直接将此运算符入栈；
 *      2、否则，若优先级比栈顶运算符的高，也将运算符压入 s1；
 *      3、否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到 （4.1） 与 s1  中新的栈顶运算符相比较
 * 5）遇到括号时：
 *      1、如果是左括号“（”，则直接压入 s1
 *      2、如果是右括号“）”，则一次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
 * 6）重复步骤 2 - 5，直到表达式的最右边
 * 7）将 s1 中剩余的运算符依次弹出并压入 s2
 * 8）依次弹出 s2 中的元素并输出，结果的逆序即为结果*/

public class I_中缀转后缀表达式 {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        List<String> list = getExpression(expression);
//        创建两个栈 s1，s2
        ReversePolish s1 = new ReversePolish(20);
        ReversePolish s2 = new ReversePolish(20);

        char agent[];

        for (String l : list) {
            if (l.matches("\\d+"))  //匹配的是多位数
                s2.push(Integer.parseInt(l));
            else if (s1.isOper(l)) {
                agent = l.toCharArray();
                while ( !s1.isEmpty() && s1.priority(s1.returnTop()) != '(' && s1.priority(agent[0]) <= s1.priority(s1.returnTop()) )
                        s2.push(s1.pop());
                s1.push(agent[0]);
            }
            else {
                if (l.equals("(")) {
                    agent = l.toCharArray();
                    s1.push(agent[0]);
                }
                else {
                    while (s1.returnTop() != '(')
                        s2.push(s1.pop());
                     s1.pop();
                }
            }
        }
        while (!s1.isEmpty())
            s2.push(s1.pop());
        s2.showStack();

    }
//  将中缀表达式 转成对应的list
    public static List<String> getExpression(String expression) {
        List<String> list = new ArrayList<>();
        int index = 0;
        String str; //对多位数的拼接
        char c;     //每遍历到一个字符，就放入到 c
        do {
//            如果是一个非数字，我需要加入到 list
            if ((c = expression.charAt(index)) < 48 || (c = expression.charAt(index)) >57) {
                list.add("" + c);
                index++;    //i需要后移
            } else {
                str = "";
                while (index < expression.length() && (c = expression.charAt(index)) >= 48 && (c = expression.charAt(index)) <= 57) {
                    str += c;   //拼接
                    index++;
                }
                list.add(str);
            }
        }while (index < expression.length());
        return list;
    }

}

/*定义一个栈，用于存放数或符号*/
class ReversePolish {
    int length;
    int[] data;
    int top = -1;

    ReversePolish(int length) {
        this.length = length;
        data = new int[this.length];
    }

    /*返回当前栈的栈顶*/
    public int returnTop() {
        return  data[top];
    }

    /*判断栈满*/
    public boolean isFull() {
        return top == length-1;
    }

    /*判断栈空*/
    public boolean isEmpty() {
        return top == -1;
    }
    /*入栈*/
    public void push(int value) {
        if (isFull())
            return;
        top++;
        data[top] = value;
    }

    /*出栈*/
    public int pop() {
        int value;
        if (isEmpty())
            throw new RuntimeException("栈空！没有数据");
        value = data[top];
        top--;
        return value;
    }

    /*遍历栈*/
    public void showStack() {
        if (isEmpty()) {
            System.out.println("栈空！");
            return;
        }
        for (int i = top; i >=0; i--) {
            char temp = (char)data[i];
            if (isOper(String.valueOf(temp)))
                System.out.print(temp);
            else
                System.out.print(data[i]);
        }

        System.out.println("");
    }

    /*返回符号的优先级*/
    public int priority(int oper) {
        if (oper == '*' || oper == '/')
            return 1;
        else if (oper == '+' || oper == '-')
            return 0;
        else
            return -1;
    }

    /*判断是不是一个运算符*/
    public boolean isOper(String value) {
        return value.equals("+")  ||  value.equals("-") ||  value.equals("*") ||  value.equals("/");
    }

    /*计算方法，num1 是栈顶的数，num2 是栈顶下面的数*/
    public int calculate(int num1, int num2, char oper) {
        if(oper == '+')
            return num1 + num2;
        else if(oper == '-')
            return num2 - num1;
        else if(oper == '*')
            return num1 * num2;
        else
            return  num2 / num1;
     }

}


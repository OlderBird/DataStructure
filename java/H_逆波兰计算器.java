package cn.aq;

/*思路：1、先将后缀表达式放入到ArrayList中
*      2、在将ArrayList传递给一个方法遍历ArrayList配合栈完成计算*/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//逆波兰计算器
public class H_逆波兰计算器 {
    public static void main(String[] args) {
//        先定义给逆波兰表达式
//        （3+4)×5-6 = 34+5×6-
//        使用java内置的栈
        String expression = "3 4 + 5 × 6 -";
        List<String> list = getExpression(expression);
        int result = calculate(list);
        System.out.println(result);

    }

//    存放字符串方法
    public static List<String> getExpression(String expression) {
        String[] split = expression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split)
            list.add(ele);
        return list;
    }

    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();    //java内置栈
        for (String l : list) {
            if (l.matches("\\d+"))  //匹配的是多位数
                stack.push(l);
            else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result;
                if (l.equals("+"))
                    result = num1 + num2;
                else if (l.equals("-"))
                    result = num2 - num1;
                else if (l.equals("×"))
                    result = num1 * num2;
                else if (l.equals("÷"))
                    result = num2 / num1;
                else
                    throw new RuntimeException("运算符有误");
                stack.push(String.valueOf(result));
            }
        }
//        最后留在 stack 栈中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }

}

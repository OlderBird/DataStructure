package cn.aq;

/*理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题
    arr【8】={0，4,7,5,2,6,1,3}，对应 arr 下标表示第几行，val 表示第 i+1 个皇后，放在第 i+1 行 的 val+1 列
*   */

//递归回溯八皇后问题
public class K_递归八皇后问题 {

    static int count = 0;
//    定义一个max表示共有多少个皇后
    int MAX = 8;
//    定义数组 array，保存皇后放置位置的结果
    int[] queen =  new int[MAX];

//    写一个方法，可以将皇后拜访的位置输出
    private void showQueen() {
        count ++;
        for (int i : queen)
            System.out.print(i + " ");
        System.out.println();
    }

//    查看当我们放置第 n 个皇后时，就去检测该皇后是否和已摆放皇后冲突
//    Math.abs：求绝对值，算法：求 k 是否等于 1？
    private  boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (queen[i] == queen[n] || Math.abs(n-i) == Math.abs(queen[n] - queen[i]) )
                return false;
        }
        return true;
    }

//    放置第 n 个皇后
    private void check(int n) {
        if (n == MAX) {
            showQueen();
            return;
        }
//        依次放入皇后，并判断是否冲突
        for (int i = 0; i < MAX ;i ++) {
//            先把当前这个皇后n，放到该行的第 1 列
            queen[n] = i;
//            判断当放置第 n 个皇后时至 i列时，是否冲突
            if (judge(n)) {
                check(n+1);
            }
//            如果冲突，继续执行 arr[n] = i，即将第 n 个皇后，放置在本行后移的第一个位置
        }
    }
    public static void main(String[] args) {
        K_递归八皇后问题 queen = new K_递归八皇后问题();
        queen.check(0);
        System.out.println(count);
    }
}

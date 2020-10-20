package cn.aq;

//递归-迷宫问题
public class J_递归迷宫回溯问题 {
    public static void main(String[] args) {
//        先创建一个二维数组，模拟迷宫
        String[][] map = new String[8][7];
//        使用 1 表示墙，且将上下左右全部置为 1
        for (int i = 0; i < 8; i ++)
            for (int j = 0; j < 7; j++)
                if (i == 0 || i == 7 || j == 0 || j == 6)
                    map[i][j] = "■";
                else
                    map[i][j] = "□";

//        在第四行，第二三列为挡板
        map[3][1] = map[3][2] = "■";
        map[2][2] = "■";

//        使用递归回溯给小球找路
        setWay(map,1,1);
//        小球走过，并标识新的地图
        showMap(map);
    }

    public static void showMap(String[][] map) {
        for (int i = 0; i < 8; i ++){
            for (int j = 0; j < 7; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
    }
//    使用递归回溯来给小球找路
//    说明：
//      1、map表示地图
//      2、i，j表示从地图的哪个位置开始出发
//      3、如果小球能找到 map[6][5] 的位置，则说明通路找到
//      4、约定，当 map[i][j] 为 □ 表示该点没有走过当为 ■表示墙，√表示通路可以走，× 表示该点已经走过，但是走不通
//      5、在走迷宫时，需要确定一个策略（方法），下→右→上→左，如果该点走不通，再回溯
    /**
     * @param map 表示地图
     * @param i 从哪个位置开始找
     * @param j 从哪个位置开始找
     * @return 如果找到通路，就返回 true，否则返回 false*/
    public static boolean setWay(String[][] map, int i, int j) {
        if (map[6][5].equals("★"))  //终点已经找到 ok
            return true;
        else
            if (map[i][j].equals("□")) {    //如果这个点还没有走过
//                按照策略 下→右→上→左 走
                map[i][j] = "★";  //假定该点可以走通
                if (setWay(map,i + 1,j))
                    return true;
                else if (setWay(map,i,j+1))
                    return true;
                else if (setWay(map,i - 1,j))
                    return true;
                else if (setWay(map,i,j - 1))
                    return true;
                else {
//                    说明该点是走不通的，是死路
                    map[i][j] = "☆";
                    return false;
                }
            }
            else {
//                如果map[i][j] ！= □，可能是其他恩赫一个
                return false;
            }
    }
}
/*如何求出最短路径？将所有可能的走法，所走出来的方法存入一个数组，数值最小的那个就是最优路径*/

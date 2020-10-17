package cn.aq;

import java.io.*;

//稀疏数组
public class A_SparseArray {
    public static void main(String[] args) throws IOException {
//        1、创建一个原始的二维数组11*11；0表示没有棋子，1表示黑子，2表示白子
        int originArray[][] = new int[11][11];
        originArray[1][2] = 1;
        originArray[2][3] = 2;
        originArray[4][5] = 2;
//        2、输出原始的二维数组
        for (int[] row : originArray) {
            for (int data : row)
                System.out.print(data);
            System.out.println();
        }
        System.out.println("===========");
/**
 * 3、将二维数组转稀疏数组
 *  （1）、先遍历二维数组得到非0数据的个数
 *  （2）、创建对应的稀疏数组（第一行）
 *  （3）、遍历二维数组，将非0的值存放到稀疏数组中*/
        int sum = 0;
        int count = 1;

        for (int[] row : originArray) {
            for (int data : row)
                if (data!=0)
                    sum++;
        }
        int sparseArray[][] = new int[sum + 1][3];
        sparseArray[0][0] = sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (originArray[i][j] != 0) {
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = originArray[i][j];
                    count ++;
                }
            }
        }
//        4、将稀疏数组恢复成原始的二维数组
        int originArray2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (count = 1; count <= sparseArray[0][2]; count++)
            originArray2[sparseArray[count][0]][sparseArray[count][1]] = sparseArray[count][2];


        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
//        5、将 originArray2[][]，存入到磁盘中
            fileWriter= new FileWriter("C:\\Users\\hp\\Desktop\\ListTest.txt");
            bufferedWriter = new BufferedWriter(fileWriter);

            for (int[] row : originArray2) {
                for (int data : row)
                    bufferedWriter.write(String.valueOf(data));
                bufferedWriter.write("\n");
            }
        }finally {
            if(bufferedWriter != null)
                bufferedWriter.close();
            if(fileWriter != null)
                fileWriter.close();

        }

//        6、从磁盘中读取并赋值给 稀疏数组sparseArray2
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("C:\\Users\\hp\\Desktop\\ListTest.txt");
            bufferedReader = new BufferedReader(fileReader);
            String str = null;
            int value = 0;
            count = 0;
            //循环输出每行数据，存储在字符串中
            while ((str = bufferedReader.readLine()) != null) {
                //遍历字符串，找到不为0的字符
                for (int i = 0; i<str.length(); i++) {
                    if ((value = Integer.parseInt(String.valueOf(str.charAt(i)))) != 0)
                        count++;
                }
            }
        }finally {
            if(bufferedReader != null)
                bufferedReader.close();
            if(fileReader != null)
                fileReader.close();
        }

    }
}

package cn.aq;

import java.util.Scanner;

/*
* 哈希表基本介绍：
* 散列表，也叫哈希表，是根据关键码值（key value）而直接进行访问的数据结构
* 也就是说，它通过把关键码值映射到表中一个位置来访问记录，以加快查找的速度
* 这个映射函数叫做散列函数，存放记录的数组叫做散列表
*
* 提出问题：
* 有一个公司，当有新的员工来报道时，要求将该员工的信息加入【id，性别，年龄，住址..】
* 当输入该员工的id时，要求查找到该员工的所有信息
* 要求：不使用数据库，尽量节省内存，速度越快越好=>哈希表【散列】
*
* 使用哈希表来管理雇员信息，value是一个链表*/
public class L_哈希表 {
    public static void main(String[] args) {
//        创建哈希表
        HashTab hashTab = new HashTab(7);

//        写一个简单的菜单
        Scanner in = new Scanner(System.in);
        int key = 0;
        int id = 0;
        while (true) {
            System.out.println("1、添加雇员\n2、查找雇员\n3、显示雇员\n4、退出系统");
            key = in.nextInt();
            switch (key) {
                case 1:
                    System.out.println("输入添加员工的id：");
                    id = in.nextInt();
                    System.out.println("输入用户名：");
                    String username = in.next();
                    System.out.println("输入密码：");
                    String password = in.next();
//                    创建雇员
                    Emp emp = new Emp(id,username,password);
                    hashTab.add(emp);
                    break;
                case 2:
                    System.out.println("输入查找员工的id：");
                    id = in.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case 3:
                    hashTab.list();
                    break;
                case 4:
                    in.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

    }
}

/*表示一个雇员bean*/
class Emp {
    public int id;
    public String username;
    public String password;
    public Emp next;
    public Emp(int id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }
    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

/*创建 EmpLinkedList，表示链表*/
class EmpLinkedList {
//    头指针，执行第一个 Emp，因此我们这个链表的head，是直接指向第一个Emp
    private Emp head;

/**    添加雇员到链表
    说明
      假定，当添加员工时，id是自增长，即 id 的分配总是从小到大
      因此我们将该员工直接加入到本链表的最后即可*/
    public void add(Emp emp) {
//        如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
//        如果不是第一个员工
        Emp curEmp = head;
        while (curEmp.next != null)
            curEmp = curEmp.next;       //后移直到最后
//        退出时直接将 emp 加入链表
        curEmp.next = emp;
    }

/**    遍历链表的雇员信息*/
    public void list(int no) {
        if (head == null) {
            System.out.println("第 "+ (no+1) +" 条链表为空");
            return;
        }
        System.out.print("第 "+ (no+1) +" 条链表信息为 ");
        Emp curEmp = head;
        while (true) {
            System.out.print(curEmp);
            if(curEmp.next == null)
                break;
            curEmp = curEmp.next;
        }
    }

/**    根据id查找雇员*/
    public Emp findEmpById(int id) {
//        判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
//        辅助指针
        Emp curEmp = head;
        while (true) {
//            找到
            if (curEmp.id == id)
                break;
//            没有找到
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

}

/*创建 HashTable，管理多条链表*/
class  HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;   //表示共有多少条链表

/**    构造器*/
    public HashTab(int size) {
        this.size = size;
//        初始化 empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
//        要分别初始化每一条链表
        for (int i = 0; i < size ; i ++)
                empLinkedListArray[i] = new EmpLinkedList();
    }

/**    添加雇员*/
    public void add(Emp emp) {
//        根据员工的id，得到该员工应当添加到哪条链表
        int index = hashFun(emp.id);
//        将 emp 添加到对应的链表中
        empLinkedListArray[index].add(emp);
    }

/**    遍历所有的链表，也就是遍历 hashtab*/
    public void list() {
        for (int i = 0; i < size; i++)
            empLinkedListArray[i].list(i);
    }

/**    根据输入的id，查找雇员*/
    public void findEmpById(int id) {
//        使用散列函数确定到哪条链表查找
        int index = hashFun(id);
        Emp emp = empLinkedListArray[index].findEmpById(id);
        if (emp != null)    //找到
            System.out.println("在第 "+index+" 条链表中找到雇员 "+id);
        else
            System.out.println("在哈希表中，没有找到该雇员");
    }

/**    编写散列函数，使用一个简单取模法*/
    public int hashFun(int id) {
        return id % size;
    }
}

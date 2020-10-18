package cn.aq;


/**分析双向链表的遍历、添加、修改、删除的操作思路 -> 代码实现
 * 1、遍历方式和单链表一样，只是可以向前、也可以向后查找
 * 2、添加（默认添加到双向链表的最后）
 *      1）先找到双向链表的最后节点
 *      2）temp.next = newNode
 *      3）newNode.pre = temp;
 * 3、修改的思路和单向链表一样
 * 4、删除
 *      1）因为是双向链表，因此我们可以实现自我删除某个节点
 *      2）直接找到要删除的这个节点，比如 temp
 *      3）temp.pre.next = temp.next
 *      4）temp.next.pre = temp.pre*/

//双向链表
public class D_双向链表 {
    public static void main(String[] args) {
        DoubleList list1 = new DoubleList(1,"艾琦");
        DoubleList list2 = new DoubleList(2,"貂蝉");
        DoubleList list3 = new DoubleList(3,"西施");
        DoubleList list4 = new DoubleList(4,"杨玉环");
        DoubleList list5 = new DoubleList(5,"外来者");

        DoDoubleList doDoubleList = new DoDoubleList();

        doDoubleList.insertList(list1);
        doDoubleList.insertList(list2);
        doDoubleList.insertList(list3);
        doDoubleList.insertList(list4);

        doDoubleList.insertListByIndex(list5,1);
        doDoubleList.showList();
        System.out.println();
        doDoubleList.delListByIndex(5);
        doDoubleList.showList();

    }
}

class DoubleList {
    public int id;      //节点代号
    public String data; //节点数据
    public DoubleList next;
    public DoubleList pre;

    DoubleList(int id, String data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "DoubleList{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}

class DoDoubleList {

    private DoubleList head = new DoubleList(0, "head");

    public DoubleList getHead() {
        return head;
    }

    /**
     * 2、添加节点到单向链表*/
    public void insertList(DoubleList newList) {
        DoubleList p = head;       //因为头节点不能动，我们需要一个临时变量
        while (true) {          //遍历链表，找到Uzi后
            if (p.next == null)
                break;
            p = p.next;
        }
        p.next = newList;
        newList.pre = p;
    }

    /**
     * 3、按照位置添加节点*/
    public void insertListByIndex(DoubleList newList, int index) {
        DoubleList p = head.next;
        int i = 1;
        while (p != null) {
            if (index == i)
                break;
            p = p.next;
            i ++;
        }
        newList.next = p;
        newList.pre = p.pre;
        p.pre.next = newList;
        p.pre = newList;
    }

    /**
     * 4、删除节点*/
    public void delListByIndex(int index) {
        int i = 1;
        DoubleList p = head.next;
        while (p != null) {
            if (index == i) {
                p.pre.next = p.next;
//                但是有危险，如果删除最后一个节点？会出现空指针异常
//                解决：如果是最后一个节点不需要执行p.next.pre = p.pre，所以加上if
                if (p.next != null)
                    p.next.pre = p.pre;
                break;
            }
            p = p.next;
            i++;
        }

    }

    /**
     * 5、显示链表，遍历*/
    public void showList() {
        DoubleList p = head.next;
        while (true) {
            if (p.next == null) {
                System.out.println(p);
                break;
            }
            System.out.println(p);
            p = p.next;
        }
    }
}

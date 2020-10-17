package cn.aq;

public class C_SingleLinkedList {
    public static void main(String[] args) {

        List list1 = new List(1,"艾琦");
        List list2 = new List(2,"蕾姆");
        List list3 = new List(4,"拉姆");
        List list4 = new List(5,"迪奥");
//        创建链表
        CreateList createList = new CreateList();
        createList.insertList(list1);
        createList.insertList(list2);
        createList.insertList(list3);
        createList.insertList(list4);
//        按编号添加
        List list5 = new List(3,"鸡掰");
        createList.insertListById(list5);
//        删除节点
        createList.delListByIndex(5);
        createList.showList();
//        反转链表
        createList.reverseList(createList.getHead());
        createList.showList();
    }
}

//1、定义 List，每个 List 对象就是一个节点
class List {
    public int id;      //节点代号
    public String data; //节点数据
    public List next;

    List(int id, String data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "List{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}

//2、定义 List 管理链表
class CreateList {
/**    1、先初始化一个头节点，头节点不要动，不存放具体的数据*/
    private List head = new List(0,"head");

/**    返回头节点*/
    public List getHead() {
        return head;
    }

/**    2、添加节点到单向链表
 *      思路：找到当前链表的最后节点，然后将最后节点的 next 指向最新的节点*/
    public void insertList (List newList) {
        List p = head;       //因为头节点不能动，我们需要一个临时变量
        while (true) {          //遍历链表，找到Uzi后
            if (p.next == null)
                break;
            p = p.next;
        }
        p.next = newList;
    }

/**    3、按照编号顺序添加节点
 *      思路：首先找到新添加节点的位置，然后根据c++的方式添加节点*/
    public void insertListById (List newList) {
        List p = head;
        while (p.next != null) {
            if (p.next.id > newList.id)
                break;
            p = p.next;
        }
        newList.next = p.next;
        p.next = newList;
    }

/**    4、删除节点
 *      被删除的节点 会被垃圾回收机制回收*/
    public void delListByIndex(int id) {
        List p = head.next;
        while (p.next != null) {
            if (p.next.id == id) {
                p.next = p.next.next;
                break;
            }
            p = p.next;
        }

    }

/**    5、显示链表，遍历*/
    public void showList () {
        List p = head.next;
        while (true) {
            if (p.next == null) {
                System.out.println(p);
                break;
            }
            System.out.println(p);
            p = p.next;
        }
    }

/*面试题*/
/**    6、链表反转
 *      思路
 *          1、先定义一个节点 reverseHead = new List();
 *          2、从头到尾遍历原来的节点，每遍历一个节点，就将其取出，并放在心的链表 reverseHead 的最前端
 *          3、原来的链表的 head.next = reverseHead.next*/
        public static void reverseList (List head) {
//            如果当前链表为空，或只有一个节点，无需反转，直接返回
            if (head.next == null || head.next.next == null)
                return;
//            遍历原来的链表
            List originHead = head.next;
            List next = null;   //指向当前节点【originHead】的下一个节点
            List reverseHead = new List(0,"");

            while (originHead != null) {
                next = originHead.next;  //先暂时保存当前节点的下一个节点，因为后面需要使用
                originHead.next = reverseHead.next; //将 originHead 的下一个节点指向新的链表的最前端
                reverseHead.next = originHead;      //将 originHead 连接到新的链表上
                originHead = next;
            }
//            将 head.next 指向 reverseHead.next，实现了单链表的反转
            head.next = reverseHead.next;
        }

}
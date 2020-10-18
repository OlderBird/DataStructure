package cn.aq;


/*构建一个单向的循环链表思路
*   1、先创建第一个节点，让 first 指向该节点，并形成环形
*   2、后面当我们每创建一个新的节点，就把该节点，加入到已有的环形链表中
*
* 遍历环形链表：结束：p.next = first*/

//单向环形链表
public class E_单向环形链表 {
    public static void main(String[] args) {

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addNode(5);      //加入5个节点
        circleSingleLinkedList.showNode();

        circleSingleLinkedList.JosephuQuestion(2,2,5);
    }
}

/*创建 Node 类表示一个节点*/
class Node {
    private int data;
    private Node next;

    Node (int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }

    public int getData() { return data; }
    public void setData(int data) { this.data = data; }
    public Node getNext() { return next; }
    public void setNext(Node next) { this.next = next; }
}

class CircleSingleLinkedList {
    //创建一个 first 节点，当前节点没有编号
    private Node first = new Node(-1);

    /*在末尾追加节点*/
    public void addNode(int sum) {
        Node p = null;  //辅助指针，帮助构建环形链表
        for (int i = 1; i <= sum; i++) {    //for 循环创建链表
            Node node = new Node(i);        //根据编号创建节点
            if (i == 1) {                    //第一个节点要是一个环状
                first = node;               //赋值
                first.setNext(first);       //first的下一个也是first
                p = first;                  //p是帮助我们构建环形链表的，所以要让p指向第一个节点
            }
            else {
                p.setNext(node);        //之前链表最后一个节点的next为node
                node.setNext(first);    //node 的下一个节点就是 first
                p = node;               //新链表最后一个节点就是node
            }
        }
    }

    /*遍历当前的环形链表*/
    public void showNode() {
        if (first == null)
            return;
//        因为 first 不能动，因此我们仍然使用一个辅助指针完成遍历
        Node p = first;
        System.out.println(p);
        while (p.getNext() != first) {
            p = p.getNext();
            System.out.println(p);
        }
    }

/** Josephu问题：
    设编号为1,2，...n 的 n 个人坐一圈，
    约定编号为 k 的人从 1 开始报数，数到 m 的那个人出列，
    他的下一位又从 1 开始报数，数到 m 的那个人又出列，
    以此类推，直到所有人出列，由此产生一个出队编号的序列

    思路：根据用户输入，生成一个出圈的序列
        1、需求创建一个辅助指针变量 last，事先应该指向环形链表的最后节点
        2、在报数之前，让 first 和 last 移动  k-1 次
        3、当报数时，让 first 和 last 指针同时移动 m-1 次
        4、这时候，可以将 first 指向的节点出圈：
            1）、first 向前移动一次，first = first.next
            2）、last.next = first
            3）、则原来 first 的节点会被垃圾回收机制回收*/
    public void JosephuQuestion(int startNum, int countNum, int total) {
//        （startNum：编号为k的节点；countNum：数到m的节点出列，total：所有节点数量）
//        先对数据校验
        if (startNum < 1 || startNum > total || first == null)
            return;
//        1、需求创建一个辅助指针变量 last，事先应该指向环形链表的最后节点
        Node last = first;
        while (last.getNext() != first)
            last = last.getNext();
//        2、在报数之前，让 first 和 last 移动  k-1 次
        for (int i = 0; i < startNum - 1; i++) {
            first = first.getNext();
            last = last.getNext();
        }

        while (true) {
            if (first == last)  //说明只剩一人
                break;
//        3、当报数时，让 first 和 last 指针同时移动 m-1 次
            for (int i = 1; i < countNum; i++) {
                first = first.getNext();
                last = last.getNext();
            }
            System.out.println("节点出圈："+first);
//        4、这时候，可以将 first 指向的节点出圈：
            first = first.getNext();
            last.setNext(first);
        }
        System.out.println("最后剩余的节点："+first.getData());
    }

}

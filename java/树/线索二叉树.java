package cn.aq.树;

/**基本说明
 * 从数据存储来看，数组存储方式和树的存储方式可以相互转换，即数组可以转换成树，树也可以转换成数组
 * 顺序存储二叉树的特点
 * 1、顺序存储二叉树通常只考虑完全二叉树
 * 2、第n个元素的左子节点为 2*n + 1,递归遍历，return  2*n+1
 * 3、第n个元素的右子节点为 2*n + 2
 * 4、第n个元素的父节点为（n-1）/2
 *
 * 线索化二叉树
 * 基本介绍
 * 1、n个结点的二叉链表中含有n+1个空指针域，公式：【2n-(n-1)=n+1】。
 * 利用二叉链表中的空指针域，存放指向该结点在某种遍历次序下的前驱节点和后继节点的指针（这种附加的指针称为“线索”）
 * 2、这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树。
 * 根据线索性质的不同，线索二叉树可分为前序二叉树、中序线索二叉树、后序线索二叉树三种
 * 3、一个结点的前一个结点称为前驱节点
 * 4、一个结点的后一个结点称为后继节点*/
public class 线索二叉树 {
    public static void main(String[] args) {

    }
}

/*定义ThreadBinaryTreeImpl 实现了线索化功能的二叉树*/
class ThreadBinaryTreeImpl {
    private ThreadBinaryTree root;
//    为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
//    在递归进行线索化时，pre 总是保留一个结点
    private ThreadBinaryTree pre = null;

    public void setRoot(ThreadBinaryTree root) {
        this.root = root;
    }

//    编写对二叉树进行中序线索化的方法
    public void threadedNodes(ThreadBinaryTree node) {
//        如果树为空，不能线索化
        if(root == null)
            return;
//        1、先线索化左子树
        threadedNodes(node.getLeft());
//        2、线索化当前结点
//        先处理当前结点的前驱结点
        if (node.getLeft() == null) {
//            让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            node.setLeftType(1);
        }
//        处理后继结点
        if (pre != null && pre.getRight() == null) {
//            让前驱结点的右指针指向当前结点
            pre.setRight(node);
//            修改前驱结点的右指针的类型
            pre.setRightType(1);
        }
/*        特别重要！！每处理一个结点后，让当前结点是下一个节点的前驱结点*/
        pre = node;

//        3、在线索化右子树
        threadedNodes(node.getRight());


    }
}

class ThreadBinaryTree {
    private int id;
    private String username;
    private ThreadBinaryTree left;
    private  ThreadBinaryTree right;
//    说明,左右指针
//    1、如果leftType == 0，表示指向左子树，如果为 1，表示指向前驱节点
//    2、如果rightType == 0，表示指向右子树，如果为 1，表示指向后继节点
    private int leftType;
    private int rightType;

    public ThreadBinaryTree (int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public ThreadBinaryTree getLeft() { return left; }
    public void setLeft(ThreadBinaryTree left) { this.left = left; }
    public ThreadBinaryTree getRight() { return right; }
    public void setRight(ThreadBinaryTree right) { this.right = right; }
    public int getLeftType() { return leftType; }
    public void setLeftType(int leftType) { this.leftType = leftType; }
    public int getRightType() { return rightType; }
    public void setRightType(int rightType) { this.rightType = rightType; }
    @Override
    public String toString() {
        return "LinkedTreeNode{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}


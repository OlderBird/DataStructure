package cn.aq.树;

public class 二叉树遍历查找删除 {
    public static void main(String[] args) {
//        先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
//        创建需要的节点
        TreeNode root = new TreeNode(1,"艾琦1");
        TreeNode node2 = new TreeNode(2,"艾琦2");
        TreeNode node3 = new TreeNode(3,"艾琦3");
        TreeNode node4 = new TreeNode(4,"艾琦4");
        TreeNode node5 = new TreeNode(5,"艾琦5");

//        说明，我们先手动创建二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        binaryTree.setRoot(root);
//        输出
        binaryTree.preOrder();

//        前序查找
        TreeNode treeNode = binaryTree.preOrderSearch(3);
        if (treeNode != null)
            System.out.println(treeNode.getId());

//        节点删除
        binaryTree.delNode(4);
        binaryTree.preOrder();
    }
}

class BinaryTree {
    private TreeNode root;
    public void setRoot(TreeNode root) {
        this.root = root;
    }
/*    前序遍历*/
    public void preOrder() {
        if (root != null)
            root.preOrder();
        else System.out.println("二叉树为空");
    }
/*    中序遍历*/
    public void infixOrder() {
        if (root != null)
            root.infixOrder();
        else System.out.println("二叉树为空");
    }
/*    后序遍历*/
    public void postOrder() {
        if (root != null)
            root.postOrder();
        else System.out.println("二叉树为空");
    }
/*    前序查找*/
    public TreeNode preOrderSearch(int index) {
        if (root != null)
            return root.preOrderSearch(index);
        else
            return null;
    }
/*    删除节点*/
    public void delNode(int index) {
        if (root != null)
//            这里判断 root 是不是就是要删除的节点
            if (root.getId() == index)
                root = null;
            else
//                如果不是则递归遍历
                root.delNode(index);
        else
            System.out.println("空树，不能删除");
    }
}

class TreeNode {
    private int id;
    private String username;
    private TreeNode left;
    private TreeNode right;
    public TreeNode(int id, String username) {
        this.id = id;
        this.username = username;
    }
    public int getId() { return id; }
    public String getUsername() { return username; }
    public TreeNode getLeft() { return left; }
    public TreeNode getRight() { return right; }
    public void setLeft(TreeNode left) { this.left = left; }
    public void setRight(TreeNode right) { this.right = right; }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

/*    前序遍历*/
    public void preOrder() {
        System.out.println(this);
//        递归向左子树前序遍历
        if (this.left != null)
            this.left.preOrder();
//        递归向右子树前序遍历
        if (this.right != null)
            this.right.preOrder();
    }

/*    中序遍历*/
    public void infixOrder() {
//        递归向左子树前序遍历
        if (this.left != null)
            this.left.infixOrder();
        System.out.println(this);
//        递归向右子树前序遍历
        if (this.right != null)
            this.right.infixOrder();
    }
/*    后序遍历*/
    public void postOrder() {
        System.out.println(this);
//        递归向左子树前序遍历
        if (this.left != null)
            this.left.postOrder();
//        递归向右子树前序遍历
        if (this.right != null)
            this.right.postOrder();
        System.out.println(this);
    }

    /**使用前序、中序、后序的方式来查询指定搞得节点
     * 前序查找思路
     * 1、先遍历当前节点的 node 是否等于要查找的节点
     *      ·如果是相等，则返回当前节点
     *      ·如果不相等，则判断当前节点的左节点是否为空，如果不为空，则递归前序查找
     * 2、如果左递归前序查找，找到节点，则返回，否则继续判断，当前的结点的右子节点是否为空，如果不为空，则向右递归前序查找
     *
     * 中序先判断左子节点→根节点→右子节点，后序先判断左子节点→右子节点→根节点*/

    /*前序遍历查找*/
    public TreeNode preOrderSearch(int index) {
//        比较当前节点是不是
        if (this.id == index) {
            return this;
        }
        TreeNode treeNode = null;
//        如果不是，判断当前节点的子节点是否为空，如果不为空，则递归查找
        if (this.left != null)
//            找不找得到都要等于
            treeNode = this.left.preOrderSearch(index);
//        判断右子节点
        if (this.right != null)
            treeNode = this.right.preOrderSearch(index);
//        如果左右子树都为空，又没有找到，则节点无法找到
        return treeNode;
    }

    /**完成删除节点的操作
     * 规定：
     *  1、如果删除的节点是叶子节点，则删除该节点
     *  2、如果删除的节点是非叶子节点，则删除该子树
     *  思路：
     *  1、因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否要删除结点，而不能取判断当前这个结点是不是需要删除结点
     *  2、如果当前结点的左子树不为空，并且左子节点就是要删除的节点，就将 this.left=null；并且返回（结束递归删除）
     *  3、如果当前结点的右子树不为空，并且右子节点就是要删除的节点，就将 this.left=null；并且返回（结束递归删除）
     *  4、如果左右都没有，则应当向右子树进行递归删除*/
    /*二叉树删除遍历，找到要删除的节点*/
    public void delNode(int index) {
//        如果是此查找结点的左子节点，则将左子节点及其子节点置空
        if (this.left != null && this.left.id == index) {
            this.left = null;
            return;
        }
//        如果是此查找结点的右子节点，则将右子节点及其子节点置空
        if (this.right != null && this.right.id == index) {
            this.right = null;
            return;
        }
//        否则先递归左子节点
        if (this.left !=null)
            this.left.delNode(index);
//        再递归右子节点
        if (this.right !=null)
            this.right.delNode(index);
    }
}

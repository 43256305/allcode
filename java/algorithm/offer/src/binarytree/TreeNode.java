package binarytree;

/**
 * @program: datastructure
 * @description: 二叉树节点
 * @author: xjh
 * @create: 2020-03-18 20:52
 **/
public class TreeNode {
    private int data;
    private TreeNode leftNode;
    private TreeNode rightNode;

    public TreeNode(){

    }

    public TreeNode(int data){
        this.data=data;
    }

    public TreeNode(int data, TreeNode l, TreeNode r){
        this.data=data;
        this.leftNode=l;
        this.rightNode=r;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }
}

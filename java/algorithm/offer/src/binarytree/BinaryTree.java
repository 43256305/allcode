package binarytree;

import java.util.Scanner;

/**
 * @program: datastructure
 * @description: 二叉树
 * @author: xjh
 * @create: 2020-03-18 21:00
 **/
public class BinaryTree {
    private TreeNode root;

    public BinaryTree(){

    }

    public BinaryTree(TreeNode root){
        this.root=root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    //先序递归创建二叉树   中->找所有左边的->右边
    private TreeNode createTree(){
        TreeNode node;
        Scanner in=new Scanner(System.in);
        String temp=in.nextLine();
        int data;
        if ("#".equals(temp)){
            return null;
        }else{
            data=Integer.valueOf(temp);
            node=new TreeNode(data);
            node.setLeftNode(createTree());
            node.setRightNode(createTree());
        }
        return node;
    }

    public TreeNode buildTree(){
        root=createTree();
        return root;
    }

    public int countLeaf(TreeNode t){
        if (t==null){
            return 0;
        }else if (t.getLeftNode()==null&&t.getRightNode()==null){
            return 1;
        }else{
            return countLeaf(t.getLeftNode())+countLeaf(t.getRightNode());
        }
    }

    public static void main(String[] args) {
        BinaryTree binaryTree=new BinaryTree();
        TreeNode root=binaryTree.buildTree();
        System.out.println(binaryTree.countLeaf(root));
    }
}

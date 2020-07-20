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




    /**
    * @Description: 根据先序和中序构建二叉树
     * 例子：
     * 前序遍历: GDAFEMHZ  左节点：GAFE
     * 中序遍历: ADEFGHMZ   左节点：ADEF
     * 原理：根据前序找出根节点，根据中序，找出左子树所有节点，右子树所有节点
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/7/8
    */
    public TreeNode reConstructBinaryTree(int[] pre ,int[] in){
        TreeNode root = reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }
    public TreeNode reConstructBinaryTree(int[] pre,int preStart,int preEnd,int[] in,int inStart,int inEnd){
        if(preStart>preEnd || inStart > inEnd){ //到达边界条件时返回null
            return null;
        }
        TreeNode  treeNode =new TreeNode(pre[preStart]);   //新建一个TreeNode

        //循环中序序列，找出中序序列的根节点，因为前序的第一个就是根节点，所以中序与前序第一个相等的节点就是根节点
        for(int i=inStart;i<=inEnd;i++){
            if(in[i] == pre[preStart]){
                // 重构左子树，注意边界条件    preStart+1到preStart+i-inStart为前序中左子树的所有节点
                //为什么要减inStart呢？因为i是从inStart开始遍历的，i-inStart就是左节点的个数
                treeNode.leftNode = reConstructBinaryTree( pre, preStart+1,preStart+i-inStart, in, inStart,i-1);
                // 重构右子树，注意边界条件
                treeNode.rightNode = reConstructBinaryTree(pre, preStart+i-inStart+1, preEnd,in,i+1,inEnd);
            }
        }
        return treeNode;
    }


    /**
    * @Description: 根据中序与后序构建二叉树
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/7/8
    */
    public TreeNode reConstructBinaryTree2(int[] in,int[] last){
        TreeNode root = reConstructBinaryTree2(in,0,in.length-1,last,0,last.length-1);
        return root;
    }

    public TreeNode reConstructBinaryTree2(int[] in,int inStart,int inEnd,int[] last,int lastStart,int lastEnd){
        if(inStart > inEnd || lastStart > lastEnd)
            return null;
        TreeNode treeNode = new TreeNode(last[lastEnd]);
        for(int i=inStart;i<=inEnd;i++){
            if(in[i] == last[lastEnd]){
                treeNode.leftNode = reConstructBinaryTree2(in, inStart,i-1,last,lastStart,lastStart+i-inStart-1);
                treeNode.rightNode = reConstructBinaryTree2(in,i+1,inEnd,last,lastStart+i-inStart,lastEnd-1);
            }
        }
        return treeNode;
    }
}

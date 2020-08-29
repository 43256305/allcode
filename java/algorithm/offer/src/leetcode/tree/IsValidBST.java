package leetcode.tree;

import leetcode.TreeNode;

import java.util.Stack;

/**
 * @program: offer
 * @description: 验证二叉搜索树
 * @author: xjh
 * @create: 2020-06-15 20:20
 **/
public class IsValidBST {

    /**
    * @Description: 中序遍历  时间复杂度：n（节点个数）
     * 原理：判断是否为二叉搜索树，如果简单的用深度优先搜索验证left<root,right>root是不行的，因为左子树要全部小于根节点，右子树要全部大于根节点
     * 而不只是父与子比较。如:[10,5,null,null,15,6,20]其中，6肯定比15小，但是身为右子树6却比根节点10小，所以错误
     * 于是考虑用中序遍历，中序遍历中，只要后面遍历的比前面的大，则一定为二叉搜索树，如上一个例子的中序为：5，10，6，15，20
     * 用last保存上一个节点
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/15
    */
    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)){
            return true;
        }
        Stack<Object> stack=new Stack<>();
        int last = Integer.MIN_VALUE;
        boolean one = true;  //标志是否为第一次赋值
        stack.push(root);
        while (!stack.isEmpty()){
            Object e = stack.pop();
            if (e == null){
                continue;
            }else if (e instanceof TreeNode){
                stack.push(((TreeNode)e).right);
                stack.push(((TreeNode)e).val);
                stack.push(((TreeNode)e).left);
            }else{  //val
                if (one){  //如果为第一次赋值（tree的第一个节点），则直接赋值，不用比较
                    last = (Integer)e;
                    one=false;
                    continue;
                }
                if ((Integer)e > last){
                    last = (Integer)e;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    class Solution2{
        
        /**
        * @Description: 递归  时间：n
         * 原理：大多数都在lower与upper之间，每次递归右节点时，lower更新为右节点的父节点（即底增大了，因为右子树的所有节点都要大于这个父节点），
         * 每次递归左节点时，upper变为父节点（即顶变小了，因为每个左子树都要小于这个父节点）
         * 一些节点没有lower与upper，如root节点不用比较，一些节点只有lower或者upper，这些节点与既有lower又有upper的节点一样，都要与自己
         * 的lower与upper比较
        * @Param: 
        * @return: 
        * @Author: xjh
        * @Date: 2020/6/15
        */
        public boolean helper(TreeNode node, Integer lower, Integer upper) {
            if (node == null) return true;

            int val = node.val;
            if (lower != null && val <= lower) return false;  //val要大于lower小于upper
            if (upper != null && val >= upper) return false;

            if (! helper(node.right, val, upper)) return false;  //右节点要大于val，小于upper
            if (! helper(node.left, lower, val)) return false;  //左节点要大于lower，小于val
            return true;
        }

        public boolean isValidBST(TreeNode root) {
            return helper(root, null, null);
        }

    }
}

package leetcode.stack;


import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: offer
 * @description: 二叉树的中序遍历
 * @author: xjh
 * @create: 2020-06-12 20:20
 **/
public class InorderTraversal {
    
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        helper(root, res);
        return res;
    }
    
    /**
    * @Description: 递归  中序遍历  时间：n
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/12
    */
    public void helper(TreeNode root, List < Integer > res) {
        if (root != null) {
            if (root.left != null) {  //先递归左节点
                helper(root.left, res);
            }
            res.add(root.val);   //递归完了左节点后加入根节点
            if (root.right != null) {  //再递归右节点
                helper(root.right, res);
            }
        }
    }

    public void helperPre(TreeNode root,List<Integer> res){  //前序遍历
        if (root!=null){
            res.add(root.val);
            if (root.left != null) helperPre(root.left,res);
            if (root.right != null) helperPre(root.right,res);
        }
    }

    /**
    * @Description: 栈加迭代 中序遍历  时间：n
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/12
    */
    public List < Integer > inorderTraversal1(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack< TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {  //往左走，直到底
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();   //最左边的出栈
            res.add(curr.val);  //最左边的操作
            curr = curr.right;  //最左边的右孩子（最左边的没有左孩子了）参加下一个循环的入栈操作
        }
        return res;
    }

    
    /**
    * @Description: 莫里斯中序遍历  时间：n
     * 把本身与它的右节点放入它的左节点的最右边  如，A（左） B（根） C（右）-> A（根） B（A右） C（B右） 直接输出ABC就是中序遍历
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/12
    */
    public List < Integer > inorderTraversal2(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { //找到cur的左子树最右边的
                    pre = pre.right;
                }
                pre.right = curr; //下面四步： 把当前节点与它的右子树加入当前节点左子树最右边节点的右节点，当前节点指向它的左节点（最后成为一个只有右子树的链表树）
                TreeNode temp = curr;
                curr = curr.left;
                temp.left = null;
            }
        }
        return res;
    }


    /**
    * @Description: 前序   标记遍历 （高效好记忆）
     * 节点入栈顺序与遍历相反，如前序则right先入队，left随后入队，val最后入队。于是，出栈时先处理后入栈的val，再处理left，最后right
     * 总之，先处理那个，那个后入栈
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/12
    */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<Object> stack = new Stack<>();  //因为要放置val和node，所以为Object
        stack.add(root);
        while (!stack.empty()) {
            Object e = stack.pop();
            if (e == null) {  //下面都没有判null，说明null节点也加入了栈
                continue;
            }
            if (e instanceof TreeNode) {
                // 前序遍历（根 、左、右），要做 逆序 入栈  （中序只需要把val放在中间即可）
                stack.add(((TreeNode) e).right);
                stack.add(((TreeNode) e).left);
                stack.add(((TreeNode) e).val);  //中序把val放在中间，则下个循环会直接先处理left
            } else if (e instanceof Integer) {
                list.add((Integer) e);  //操作val值
            }
        }

        return list;
    }
}

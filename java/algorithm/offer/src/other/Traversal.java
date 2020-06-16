package other;

import leetcode.TreeNode;

import java.util.ArrayDeque;

/**
 * @program: offer
 * @description:深度优先和广度优先遍历
 * @author: xjh
 * @create: 2020-06-15 19:16
 **/
public class Traversal {

    public void depthOrderTraversal(TreeNode root){
        if(root==null){
            System.out.println("empty tree");
            return;
        }
        ArrayDeque<TreeNode> stack=new ArrayDeque<TreeNode>();
        stack.push(root);
        while(stack.isEmpty() == false){
            TreeNode node=stack.pop();   //节点出栈并操作此节点
            System.out.print(node.val+" ");
            if(node.right!=null){   //左右节点入栈
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }
        System.out.print("\n");
    }

    public void levelOrderTraversal(TreeNode root){
        if(root==null){
            System.out.println("empty tree");
            return;
        }
        ArrayDeque<TreeNode> queue=new ArrayDeque<TreeNode>();
        queue.add(root);
        while(queue.isEmpty()==false){
            TreeNode node=queue.remove();  //节点出队并操作
            System.out.print(node.val+" ");
            if(node.left!=null){  //左右节点入队
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
        System.out.print("\n");
    }
}

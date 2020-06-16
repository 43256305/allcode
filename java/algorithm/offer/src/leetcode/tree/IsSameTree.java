package leetcode.tree;

import leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: offer
 * @description: 相同的树
 * @author: xjh
 * @create: 2020-06-16 09:29
 **/
public class IsSameTree {

    class Solution1{
        
        /**
        * @Description: 递归   时间：n
         * 出口：一个为null，一个不是（false），都为null（true），值不同（false）  继续递归：都不为null，且val相同
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/16
        */
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p!=null && q!=null){
                if (p.val == q.val){
                    return isSameTree(p.right,q.right)&&isSameTree(p.left,q.left);
                }else {
                    return false;
                }
            }else if (p == null && q == null){
                return true;
            }else{
                return false;
            }
        }
    }

    class Solution2{

        /**
        * @Description: 按层次遍历  bfs
         * 定义两个队列保存两棵树，循环出队，只要满足e1不为null，e2不为null，就要把他们的左右节点分别加入队列（就算是null也要加入，这样
         * 才能保证结构完全一样）
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/16
        */
        public boolean isSameTree(TreeNode p, TreeNode q) {
            Queue<TreeNode> queue1 = new LinkedList<>();
            Queue<TreeNode> queue2 = new LinkedList<>();
            queue1.offer(p);
            queue2.offer(q);

            while (!queue1.isEmpty() && !queue2.isEmpty()){
                TreeNode e1 = queue1.poll();
                TreeNode e2 = queue2.poll();
                if (e1==null && e2==null){
                    continue;
                }
                if (e1 != null && e2!=null){
                    if (e1.val == e2.val){
                        queue1.offer(e1.right);
                        queue1.offer(e1.left);
                        queue2.offer(e2.right);
                        queue2.offer(e2.left);
                        continue;
                    }
                    return false;
                }
                return false;
            }

            if (queue1.isEmpty() && queue2.isEmpty()){
                return true;
            }else{
                return false;
            }
        }
    }
}

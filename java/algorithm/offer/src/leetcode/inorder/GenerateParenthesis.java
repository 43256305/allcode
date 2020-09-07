package leetcode.inorder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: offer
 * @description: 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的 括号组合。
 * @author: xjh
 * @create: 2020-06-16 16:55
 **/
public class GenerateParenthesis {
    private static GenerateParenthesis parenthesis = new GenerateParenthesis();

    public static void main(String[] args) {
        GenerateParenthesis.Solution3 solution3 = parenthesis.new Solution3();
        solution3.generateParenthesis(3);
    }

    class Solution1{
        
        /**
        * @Description: 深度优先遍历（使用了递归这个系统栈）
         * 原理：回溯+剪枝  每一步都有两种选择，即增加左/右括号，我们每种选择都试一下，把不符合条件的选择剪枝，把最后得到结果的放入结果集
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/16
        */
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            // 特判
            if (n == 0) {
                return res;
            }

            // 执行深度优先遍历，搜索可能的结果
            dfs("", n, n, res);
            return res;
        }

        /**
         * @param curStr 当前递归得到的结果
         * @param left   左括号还有几个可以使用
         * @param right  右括号还有几个可以使用
         * @param res    结果集
         *
         * 路径：curStr，left，right（代表已经做出的选择）  选择列表：left或者right减1   结束条件：left==0&&right==0
         */
        private void dfs(String curStr, int left, int right, List<String> res) {
            // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
            // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
            if (left == 0 && right == 0) {
                res.add(curStr);
                return;
            }

            // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
            //而右括号大于等于左括号是合法的，如n=2 str=(  left=1 right=2这是可以的
            if (left > right) {
                return;
            }

            if (left > 0) {  //去除一个左括号的情况
                dfs(curStr + "(", left - 1, right, res);   //可以直接用string去+，而不用StringBuilder类型，因为String类型相加
                // 就是新建一个String，这样不会影响到下面加右括号的场景
            }

            if (right > 0) {  //去除一个右括号的情况  注意上面去除左括号并不影响下面，left还是没变，这只是两种情况
                dfs(curStr + ")", left, right - 1, res);
            }
        }
    }

    class Solution2{
        class Node {
            /**
             * 当前得到的字符串
             */
            private String res;
            /**
             * 剩余左括号数量
             */
            private int left;
            /**
             * 剩余右括号数量
             */
            private int right;

            public Node(String str, int left, int right) {
                this.res = str;
                this.left = left;
                this.right = right;
            }
        }


        /**
        * @Description: 广度优先遍历  队列
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/16
        */
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            if (n == 0) {
                return res;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.offer(new Node("", n, n));

            while (!queue.isEmpty()) {

                Node curNode = queue.poll();
                if (curNode.left == 0 && curNode.right == 0) {
                    res.add(curNode.res);
                }
                if (curNode.left > 0) {
                    queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
                }
                if (curNode.right > 0 && curNode.left < curNode.right) {
                    queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
                }
            }
            return res;
        }
    }

    class Solution3{
        
        /**
        * @Description: 动态规划
         * dp[i]:使用i对括号能生成的组合，dp[i]='('+dp[可能的括号对数]+')'+dp[剩下的括号对数]="(" + dp[j] + ")" + dp[i- j - 1]
         * 初始状态:dp[0]=""
         * 状态转移方程理解：其中，可能的括号对数和剩下的括号对数总括号之和i-1，因为外面有一对括号。如果设可能的括号对数为j，那么剩下
         * 的括号对数就为(i-1)-j
        * @Param:
        * @return: 
        * @Author: xjh
        * @Date: 2020/6/16
        */
        public List<String> generateParenthesis(int n) {
            if (n == 0) {
                return new ArrayList<>();
            }
            // 这里 dp 数组我们把它变成列表的样子，方便调用而已
            List<List<String>> dp = new ArrayList<>(n);

            List<String> dp0 = new ArrayList<>();
            dp0.add("");
            dp.add(dp0);

            for (int i = 1; i <= n; i++) {
                List<String> cur = new ArrayList<>();
                for (int j = 0; j < i; j++) {  //总括号对数为i时，可能的括号对数为j
                    List<String> str1 = dp.get(j);  //可能
                    List<String> str2 = dp.get(i - 1 - j);  //剩余
                    //当i=2，j=0时 str1=dp[0]="" str2=dp[1]="()"  结果为：()()  当i=2，j=1时，str1=dp[1]="()" str2=dp[0]=""  结果为(())
                    //其实就是枚举括号个数从0到i（不包括）的组合，与从i-1-j到0的组合的各种排列情况
                    for (String s1 : str1) {
                        for (String s2 : str2) {
                            // 枚举右括号的位置
                            cur.add("(" + s1 + ")" + s2);
                        }
                    }
                }
                dp.add(cur);
            }
            return dp.get(n);
        }
    }
}

package leetcode.inorder;

import java.util.Stack;

/**
 * @program: offer
 * @description:最长有效括号
 * @author: xjh
 * @create: 2020-06-22 16:43
 **/
public class LongestValidParentheses {

    public class Solution1 {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<Character>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push('(');
                } else if (!stack.empty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
            return stack.empty();
        }

        /**
        * @Description: 暴力法
         * 变量：i(0,i<n),j(i+2,j<n)
         * 从i开始，每次增加两个，判断i-j的子序列是否有效。循环一轮后，i前进一位，再次循环。
         * 判断有效：即括号一一配对就是有效，右括号多或者不配对都不是有效
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/22
        */
        public int longestValidParentheses(String s) {
            int maxlen = 0;
            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 2; j <= s.length(); j+=2) {
                    if (isValid(s.substring(i, j))) {
                        maxlen = Math.max(maxlen, j - i);
                    }
                }
            }
            return maxlen;
        }
    }

    public class Solution2 {

        /**
        * @Description: 栈
         *遇到'('时，把下标入栈，遇到')'时，弹出栈顶元素，判断栈是否为空(如果只有一对括号如"()"，第二个括号入栈时，第一个括号
         * 出栈后，栈是不为空的，因为刚开始-1就入栈了)，为空则把当前下标入栈，不为null则把当前下标减去栈顶元素就得到了长度。
         * 被减的下标位置并不是与')'匹配的'('，而是与他匹配的'('前面的一位下标。
         * 这样保证了只有有效的括号才会相减，没有效的括号是不会参与计算的，如"(()))" 遇到第三个)时，栈顶已经为空了，所以不参与计算
         * 如"()((()))"会计算最后一个)下标与-1之间的差
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/22
        */
        public int longestValidParentheses(String s) {
            int maxans = 0;
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.empty()) {
                        stack.push(i);  //如果栈已经为空了还出现)符号，说明这个符号是无效的，我们要记录它的下标，下一次计算时后面
//                        有效的括号会减去它的下标，而不是-1
                    } else {
                        maxans = Math.max(maxans, i - stack.peek());  //注意这里减的栈顶元素是上面出栈后的剩下的栈顶元素
                    }
                }
            }
            return maxans;
        }
    }

    public class Solution3 {
        
        /**
        * @Description: 动态规划
         *dp[i] 表示以下标为 i 的字符结尾的最长有效子字符串的长度，以 ‘(’ 结尾的子字符串对应的 dp 数组位置上的值必定为 0
         * 递推式：
         *s[i]=')' 且 s[i-1]='('  则：dp[i]=dp[i-2]+2   即以一对括号结尾
         * s[i]=')'  且 s[i-1]=')' 且 s[i-dp[i-1]-1]='('  则：dp[i]=dp[i-1]+dp[i-dp[i-1]-2]+2   即以两个右括号结尾  dp[i-1]
         * 为第一个右括号包括的括号对数，dp[i-dp[i-1]-2]为除去这两个括号以及中间包括字符左边的括号对数，2为i位置的括号
         * 解释：如"(())" i=3,dp[3-dp[2]-1],因为2和1位置上有1对括号，所以dp[2]=2所以3-dp[2]-1=0，dp[0]='('，所以dp[3]=2+dp[0]+2=4
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/22
        */
        public int longestValidParentheses(String s) {
            int maxans = 0;
            int dp[] = new int[s.length()];
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;  //如果i-2<0，则直接取0+2
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    maxans = Math.max(maxans, dp[i]);
                }
            }
            return maxans;
        }
    }

    public static void main(String[] args) {
        LongestValidParentheses parentheses = new LongestValidParentheses();
        LongestValidParentheses.Solution2 solution2 = parentheses.new Solution2();
        solution2.longestValidParentheses("()(()()");
    }

}

package leetcode.dynamicprogramming;

import java.util.*;

/**
 * @program: offer
 * @description: 单词拆分   https://leetcode-cn.com/problems/word-break/solution/dan-ci-chai-fen-by-leetcode/
 * @author: xjh
 * @create: 2020-06-02 14:07
 **/
public class WordBreak {

    /**
    * @Description: 动态规划
     * 变量：i(1,i<=n)，j(0,j<i)
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/2
    */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);   //list化为hashset，contains方法时间复杂度降低为1
        //dp数组：s=dog dic={do,g},长度为4的dp数组：{t,f,t,t}表示0到1可以找到，2到2为可以找到
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                //当j位置为true且字典包含j-i的子串时，i位置才为true
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
    * @Description: 宽度优先搜索
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/2
    */
    public boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        //visited表示i处的字符串有没有检查，没有则为0
        int[] visited = new int[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start] == 0) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = 1;
            }
        }
        return false;
    }

}

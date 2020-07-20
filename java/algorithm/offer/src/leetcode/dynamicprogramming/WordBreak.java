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
     * dp[i]=true表示从0到i的字符串拆分成若干段存在于字典中
     * 原理：从下标1开始，逐渐给dp数组赋值。  递推关系：当为i位置的dp数组赋值时，从j=0开始遍历数组，直到i，看是否存在0到j字符串存在字典
     * 中，并且j到i的字符串也存在与字典中，都满足，则dp[i]=true
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/2
    */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);   //list化为hashset，contains方法时间复杂度降低为1
        //dp数组：s=dog dic={do,g},长度为4的dp数组：{true,false,true,true}表示0到1可以找到，2到2为可以找到
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {  //从下标1开始，逐渐给dp数组赋值。
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

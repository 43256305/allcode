package leetcode.inorder;

/**
 * @program: offer
 * @description: KMP算法
 * @author: xjh
 * @create: 2020-06-22 11:28
 **/
public class KMP {

    private int[][] dp;
    private String pat;
    /**
    * @Description: 构建next表
     * dp[j][c]=next数组j为当前状态，c为当前状态遇到的字符，next为下一个状态，如果j位置上的字符==c，则next为当前状态前进一格，否则，
     * 状态回退到影子状态  如AB，j=0，c='A'，A==charAt(0)，说明前进一格dp[0]['A']=1，注意，字符会自动转换为阿斯克码
     * X为影子状态，j为当前状态，影子状态前缀与当前状态后缀相同，如果当前状态遇到的字符不匹配，则返回影子状态
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/22
    */
    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        // dp[状态][字符] = 下个状态
        dp = new int[M][256];
        // base case
        dp[0][pat.charAt(0)] = 1;  //因为java数组默认就为0，所以第一行只需要赋值一个前进状态就可以了（第一行除了前进就是回退到0）
        // 影子状态 X 初始为 0
        int X = 0;
        // 构建状态转移图
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 256; c++) {   //阿斯克码总共有256个
                dp[j][c] = dp[X][c];   //c字符与期待字符不匹配，则回退到影子状态
            }
            dp[j][pat.charAt(j)] = j + 1;  //匹配，则前进一个状态  这里没有判断c==charAt(j)，而是直接赋值
            // 更新影子状态
            X = dp[X][pat.charAt(j)];  //更新X为：上一个影子状态X遇到此时的j位置字符时的下一个状态
        }
    }

    public int search (String txt){
        int M = pat.length();
        int N = txt.length();
        // pat 的初始态为 0
        int j = 0;
        for (int i = 0; i < N; i++) {
            // 计算 pat 的下一个状态
            j = dp[j][txt.charAt(i)];
            // 到达终止态，返回结果
            if (j == M) return i - M + 1;
        }
        // 没到达终止态，匹配失败
        return -1;
    }


    static class Solution2{
        /**
         * @Description: 一维数组的next数组
         * 前缀与后缀：如ABCDAB的前缀为[A, AB, ABC, ABCD, ABCDA]，后缀为[BCDAB, CDAB, DAB, AB, B]，共有元素为"AB"，长度为2
         * 所以，下面的例子中，当匹配到P位置不匹配时，应该前进6-2=4位，并从C位置（2）开始匹配(text不回退，依然指向末尾的D)
         * text:ABCDABD    ABCDABD
         * pett:ABCDABP        ABCDABP
         * @Param:
         * @return:
         * @Author: xjh
         * @Date: 2020/6/22
         */
        void getNext(String pattern, int next[]) {
            int j = 0;
            int k = -1;   //k在j前面，代表前缀，j代表后缀
            int len = pattern.length();
            next[0] = -1;

            while (j < len - 1) {
                if (k == -1 || pattern.charAt(k) == pattern.charAt(j)) { //前缀与后缀相同，都前进一格，并更新next表
                    j++;
                    k++;
                    next[j] = k;
                } else {  //前缀与后缀不同
                    // 比较到第K个字符，说明p[0——k-1]字符串和p[j-k——j-1]字符串相等，而next[k]表示
                    // p[0——k-1]的前缀和后缀的最长共有长度，所以接下来可以直接比较p[next[k]]和p[j](即跳过前面的相同前后缀)
                    k = next[k];
                }
            }

        }

        int search(String s, String pattern) {
            int i = 0;   //指向text即s的指针
            int j = 0;   //指向pattern的指针
            int slen = s.length();
            int plen = pattern.length();

            int[] next = new int[plen];

            getNext(pattern, next);

            while (i < slen && j < plen) {

                if (s.charAt(i) == pattern.charAt(j)) {  //相等则前进一格
                    i++;
                    j++;
                } else {
                    if (next[j] == -1) {   //当next数组中pattern的j位置为-1时，text前进一个，pattern从新开始匹配
                        i++;
                        j = 0;
                    } else {
                        j = next[j];  //否则，回退到影子状态
                    }

                }

                if (j == plen) {  //匹配成功
                    return i - j;
                }
            }
            return -1;
        }
    }


    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        int[] arr = new int[7];
        solution2.getNext("ABCDABD",arr);
    }
}

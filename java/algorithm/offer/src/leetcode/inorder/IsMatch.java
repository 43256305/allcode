package leetcode.inorder;

/**
 * @program: offer
 * @description: 正则表达式匹配
 * @author: xjh
 * @create: 2020-06-16 15:30
 **/
public class IsMatch {
    private static IsMatch match = new IsMatch();

    public static void main(String[] args) {
        IsMatch.Solution1 solution1=match.new Solution1();
        solution1.isMatch("aa","a*");
    }

    class Solution1{
        
        /**
        * @Description: 动态规划  从低向上
         * dp[i][j]数组表示 text[i:]与pattern[j:]是否匹配   递推公式：当后一位不是*时，s[i]==p[j]&&dp[i+1][j+1]，即当前字符要匹配，当
         * 前字符后面的字符也要匹配   当后一位是*时，dp[i][j+2]即*为0时也匹配，说明现在也匹配   或者s[i]==p[j]&&dp[i+1][j]即当前字符匹配
         * 且p的当前字符后面的字符串与s后退一位后面的字符串也匹配（为什么i要后退一位呢？s=bbbbbbba p=b*a  s正在匹配a与b*a时，显然，根据
         * p后退两位可以匹配说明此位置为true（i=7 j=0），那么后面这么多个b的匹配，s=ba，p=b*a显然，i后退一位为a是匹配的，那么此位置也为true
         * （i=6，j=0），循环下去，当i=0，j=0时，也是i后退一位就可以得出是否匹配）
        * @Param: 
        * @return: 
        * @Author: xjh
        * @Date: 2020/6/16
        */
        public boolean isMatch(String text, String pattern) {
            boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
            dp[text.length()][pattern.length()] = true;   //右下角设为true

            //从最下面一行倒数第二列开始向左边填表（即i不变，j一直减少），填完最下面一行后向上一行
            for (int i = text.length(); i >= 0; i--){
                for (int j = pattern.length() - 1; j >= 0; j--){
                    //判断i与j是否可以匹配
                    boolean first_match = (i < text.length() &&     //如果i在text的范围内，且j与i的字符相同或者j为‘.’就为true，否则false
                            (pattern.charAt(j) == text.charAt(i) ||
                                    pattern.charAt(j) == '.'));
                    //若j的后一个字符为*，判断j的*后面的所有字符串与i及i后面的所有字符串是否匹配（即*取0的时候），匹配则直接当前位置赋值true
                    // 不匹配则判断当前字符是否匹配并且判断i后退一位的字符串(即同一列下一行)是否与j及他后面的字符串匹配，都匹配则为true
                    //如text=bba pattern=b*a i=0 j=0时，显然*为0时，不匹配，而当前i/j是匹配的，那么判断ba 与b*a是否匹配，显然也是匹配的，所以bba与b*a匹配
                    if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                        dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                    } else {
                        //没有*号，则判断当前i/j是否匹配，与i/j后面的字符是否也匹配，都匹配则为真
                        dp[i][j] = first_match && dp[i+1][j+1];
                    }
                }
            }
            return dp[0][0];
        }
    }

    class Solution2{

        /**
        * @Description: 回溯  递归
         * 出口：都为null   递归：只有两个字符或者第二个字符不为*（检查第一位，递归后面的所有字符串），不止两个字符且第二个字符为*（p后退
         * 两位，或者当前字符相等且s后退一位）
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/16
        */
        public boolean isMatch(String text, String pattern) {
            if (pattern.isEmpty()) return text.isEmpty();  //两个都为null，返回true，回溯停止
            boolean first_match = (!text.isEmpty() &&   //匹配第一个字符
                    (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

            if (pattern.length() >= 2 && pattern.charAt(1) == '*'){  //不只两个字符或者第二个字符是*号
                //第一个isMatch是匹配*为0的时候，即text直接与pattern去除前两位后的字符串比较
                //或者判断第一个字符是否相等且text后退一位与当前pattern是否相等    与上面动态规划相同逻辑
                return (isMatch(text, pattern.substring(2)) ||
                        (first_match && isMatch(text.substring(1), pattern)));
            } else {  //如果只有两个字符或者第二个字符不是*号，则只需要看看第二个字符后面的是否匹配
                return first_match && isMatch(text.substring(1), pattern.substring(1));
            }
        }
    }
}

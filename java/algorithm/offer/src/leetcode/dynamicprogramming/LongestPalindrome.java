package leetcode.dynamicprogramming;

/**
 * @program: offer
 * @description: 最大回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
 * @author: xjh
 * @create: 2020-06-01 14:27
 **/
public class LongestPalindrome {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("123455xbabad"));
        System.out.println(longestPalindrome1("123455xbabad"));
        System.out.println("s".substring(0,0).length());
    }

    /**
    * @Description: 暴力法  n^3
     * 枚举所有长度大于等于2的子串，逐个判断是否是回文子串
     * i （i<n-1）  j (j<n)
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/1
    */
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
        char[] charArray = s.toCharArray();

        // 枚举所有长度大于 1 的子串 charArray[i..j]
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {    //注意maxlen是会变化的，永远都是和当时最大的回文子串长度比较，如果去掉了，结果是最后一个回文子串，而不是最长回文子串
                if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);  //如begin=1，长度为2，则截取1，2上的元素
    }

    /**
     * 验证子串 s[left..right] 是否为回文串
     */
    private static boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {  //不能等于，如果为奇数的画，中间的一个不用判断，如aba
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
    * @Description: 动态规划   n^2
     * 变量：i（i=0,i<j），j（j=1,j<length）在字符串数组中代表从i到j的子串，在二维数组中，代表从i到j的子串是否为回文。
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/1
    */
    public static String longestPalindrome1(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len; i++) {  //对角线为什么设为true呢？比如11，22，33这些对角线位置都只有一个字母，当然可以看成是回文子串
            dp[i][i] = true;
        }
        //循环：abcd，循环1：比较a与b，循环2：比较c与a，c与b，循环3：比较d与a，d与b，d与c（在表格中体现为竖着填表，只填二维数组的右上三角形）
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {  //为什么i必须小于j？因为dp[i][j]代表i到j字符串是否为回文子串，所以i一定要小于j
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {   //两个字母相等，且他们的长度也小于等于3，则肯定是回文，如aa，aba
                        dp[i][j] = true;
                    } else {
                        //动态规划关系式（即i/j等于他左下对角线上的值）
                        dp[i][j] = dp[i + 1][j - 1];   //比如0与3位置相等，那么怎么证明他是回文呢？看0+1=1与3-1=2是否是回文串。
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
    * @Description: 中心扩散法  n^2
     * 变量：i（0，i<n-1）每一个数，都要经过奇数扩散和偶数扩散（与它的前一个数偶数扩散，所以i不能等于n-1），并且比较那个长度长选那个。
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/1
    */
    public static String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        String res = s.substring(0, 1);
        // 中心位置枚举到 len - 2 即可
        for (int i = 0; i < len - 1; i++) {
            String oddStr = centerSpread(s, i, i);
            String evenStr = centerSpread(s, i, i + 1);
            String maxLenStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            if (maxLenStr.length() > maxLen) {
                maxLen = maxLenStr.length();
                res = maxLenStr;
            }
        }
        return res;
    }

    /**
    * @Description: 奇数时：从某个点本身开始扩散直到越界判断这个点是否为某个回文子串的中点，偶数时：从left与right两个数本身开始扩散。
     * 最后返回以left与right为中点的最大回文子串
     * 变量：i(left,i>=0)，j(right,j<len)
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/4
    */
    private static String centerSpread(String s, int left, int right) {
        // left = right 的时候，此时回文中心是一个字符，回文串的长度是奇数
        // right = left + 1 的时候，此时回文中心是一个空隙，回文串的长度是偶数
        int len = s.length();
        int i = left;
        int j = right;
        while (i >= 0 && j < len) {
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            } else {
                break;
            }
        }
        // 这里要小心，跳出 while 循环时，恰好满足 s.charAt(i) != s.charAt(j)，因此不能取 i，不能取 j
        return s.substring(i + 1, j);   //当i=0，j=0时，经过上面后，变为i=-1，j=1，最后substring(0,1)所以恰好就取了一个数，当i=0，j=1时，如果不是回文，substring（1，1）返回0长度字符串
    }

    /**
    * @Description: Manacher 算法
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/1
    */
    public static String longestPalindrome3(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }

        // 得到预处理字符串
        String str = addBoundaries(s, '#');
        // 新字符串的长度
        int sLen = 2 * len + 1;

        // 数组 p 记录了扫描过的回文子串的信息
        int[] p = new int[sLen];

        // 双指针，它们是一一对应的，须同时更新
        int maxRight = 0;
        int center = 0;

        // 当前遍历的中心最大扩散步数，其值等于原始字符串的最长回文子串的长度
        int maxLen = 1;
        // 原始字符串的最长回文子串的起始位置，与 maxLen 必须同时更新
        int start = 0;

        for (int i = 0; i < sLen; i++) {
            if (i < maxRight) {
                int mirror = 2 * center - i;
                // 这一行代码是 Manacher 算法的关键所在，要结合图形来理解
                p[i] = Math.min(maxRight - i, p[mirror]);
            }

            // 下一次尝试扩散的左右起点，能扩散的步数直接加到 p[i] 中
            int left = i - (1 + p[i]);
            int right = i + (1 + p[i]);

            // left >= 0 && right < sLen 保证不越界
            // str.charAt(left) == str.charAt(right) 表示可以扩散 1 次
            while (left >= 0 && right < sLen && str.charAt(left) == str.charAt(right)) {
                p[i]++;
                left--;
                right++;

            }
            // 根据 maxRight 的定义，它是遍历过的 i 的 i + p[i] 的最大者
            // 如果 maxRight 的值越大，进入上面 i < maxRight 的判断的可能性就越大，这样就可以重复利用之前判断过的回文信息了
            if (i + p[i] > maxRight) {
                // maxRight 和 center 需要同时更新
                maxRight = i + p[i];
                center = i;
            }
            if (p[i] > maxLen) {
                // 记录最长回文子串的长度和相应它在原始字符串中的起点
                maxLen = p[i];
                start = (i - maxLen) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }


    /**
     * 创建预处理字符串
     *
     * @param s      原始字符串
     * @param divide 分隔字符
     * @return 使用分隔字符处理以后得到的字符串
     */
    private static String addBoundaries(String s, char divide) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        if (s.indexOf(divide) != -1) {
            throw new IllegalArgumentException("参数错误，您传递的分割字符，在输入字符串中存在！");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(divide);
            stringBuilder.append(s.charAt(i));
        }
        stringBuilder.append(divide);
        return stringBuilder.toString();
    }

}

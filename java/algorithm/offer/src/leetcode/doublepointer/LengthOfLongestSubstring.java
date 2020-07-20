package leetcode.doublepointer;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: offer
 * @description: 无重复字符的最长子串
 * @author: xjh
 * @create: 2020-06-08 19:46
 **/
public class LengthOfLongestSubstring {

    /**
    * @Description: 滑动窗口
     * 变量：i（0,i<n）左指针 rk（0,rk<n）右指针  左指针移动移除上一个字符，右指针移动加入字符直到出现相同的字符
     * i与rk之间的字符为无重复字符的子串，因为一旦子串中有重复字符，rk会停止前进，i就会一直前进并抛弃字符，直到没有重复字符，rk才能前进
     * ，rk前进直到又有了重复字符，就停止，这时就是一个局部最长子串。我们只需要把所有局部最长子串找出来，比较大小就行了。
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/8
    */
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为0，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = 0, ans = 0;
        for (int i = 0; i < n; ++i) {   //i为左指针
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk < n && !occ.contains(s.charAt(rk))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串（rk一直前进，直到停止，就是一个局部最长子串）
            ans = Math.max(ans, rk - i );
        }
        return ans;
    }
}

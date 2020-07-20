package leetcode.inorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @program: offer
 * @description: 串联所有单词的子串
 * @author: xjh
 * @create: 2020-07-06 16:52
 **/
public class FindSubstring {

    class Solution1{
        /**
        * @Description:  hashmap  时间复杂度：n平方
         * 原理：使用两个hashmap，一个hashmap包含所有word，并且每个word的value都为，第二个map包含了从i开始匹配到的word，匹配
         * 到一次，则赋值为1，匹配到同一个word多次，则每次赋值都在原来的value上加一  即getOrDefault()方法，最后比较两个map是否
         * 相等   使用getOrDefault()的好处：如果字符串不包含word，或者包含同一个word多次，那么两个map都不会相等
         * 变量：i（0到length-all-len 字符串s从0开始匹配，每次加1），j（0到all_len  每次匹配one_word长度的字符串）
        * @Param: 
        * @return: 
        * @Author: xjh
        * @Date: 2020/7/6
        */
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> res = new ArrayList<>();
            if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
            HashMap<String, Integer> map = new HashMap<>();
            int one_word = words[0].length();
            int word_num = words.length;
            int all_len = one_word * word_num;
            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);  //getOrDefault(k,v)如果map中已经put了k，则返回
                //此key的value，否则返回v  所以这里都给word赋值了1
            }
            for (int i = 0; i < s.length() - all_len + 1; i++) {
                String tmp = s.substring(i, i + all_len);  //直接从i开始截取all_len长度的子串
                HashMap<String, Integer> tmp_map = new HashMap<>();
                for (int j = 0; j < all_len; j += one_word) {
                    String w = tmp.substring(j, j + one_word);  //从tmp中每次截取one_word长度的子串
                    tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                    //使用getOrDefault()的好处：如果字符串不包含word，或者包含同一个word多次，那么两个map都不会相等
                }
                if (map.equals(tmp_map)) res.add(i);  //如果两个map相等，则说明从i开始完全匹配
            }
            return res;
        }
    }

    class Solution2{
        
        /**
        * @Description: 滑动窗口  时间复杂度：n
        * @Param: 
        * @return: 
        * @Author: xjh
        * @Date: 2020/7/6
        */
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> res = new ArrayList<>();
            if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
            HashMap<String, Integer> map = new HashMap<>();
            int one_word = words[0].length();
            int word_num = words.length;
            int all_len = one_word * word_num;
            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            for (int i = 0; i < one_word; i++) {
                int left = i, right = i, count = 0;
                HashMap<String, Integer> tmp_map = new HashMap<>();
                while (right + one_word <= s.length()) {
                    String w = s.substring(right, right + one_word);
                    right += one_word;
                    if (!map.containsKey(w)) {  //如果不包含，则count清零，tmp_map清空
                        count = 0;
                        left = right;
                        tmp_map.clear();
                    } else {
                        tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                        count++;
                        //下面while为true，表示w这个单词匹配了多次，则left右移
                        while (tmp_map.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
                            String t_w = s.substring(left, left + one_word);
                            count--;
                            tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                            left += one_word;
                        }
                        if (count == word_num) res.add(left);
                    }
                }
            }
            return res;
        }
    }
}

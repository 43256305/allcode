package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: offer
 * @description: 重复的DNA序列
 * @author: xjh
 * @create: 2020-06-09 16:42
 **/
public class FindRepeatedDnaSequences {

    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();
        Set<String> res = new HashSet<>();  //有重复子串的子串加入res  重复子串也加入集合，而不是list，是为了去重
        Set<String> set = new HashSet<>();  //所有的子串都加入set中
        for (int i = 0; i <= len - 10; i++) {
            String key = s.substring(i, i + 10);
            //之前是否存在   判断重复子串是用set的特性帮我们，如果是重复的，则set中一定包含
            if (set.contains(key)) {  //判断子串是否曾经加入过，如果加入过（说明重复子串），就加入res
                res.add(key);
            } else {
                set.add(key);
            }

        }
        return new ArrayList<>(res);
    }


    /**
    * @Description:
     * 把字符串编码为数字序列，然后通过移位保留之前的信息   优化：原来的set是存储字符串，现在的set存储整数，contains方法更快
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/9
    */
    public List<String> findRepeatedDnaSequences1(String s) {
        int len = s.length();
        if (len == 0 || len < 10) {
            return new ArrayList<>();
        }
        Set<String> res = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        char map[] = new char[26];
        map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;
        int key = 0;
        char[] array = s.toCharArray();
        //第一组单独初始化出来
        for (int i = 0; i < 10; i++) {
            key = key << 2 | map[array[i] - 'A'];   //每次把A/C/G/T的字母的二进制值放入key中，如开始key为0，遇到C，则变为01，
            // 在遇到G移位|操作变为1001  （0|x=x）
        }
        set.add(key);
        for (int i = 10; i < len; i++) {
            key <<= 2;
            key |= map[array[i] - 'A'];  //key的后两位加入新字母
            key &= 0xfffff;  //只取后20位，前12都置为0
            if (set.contains(key)) {
                res.add(s.substring(i - 9, i + 1));
            } else {
                set.add(key);
            }

        }
        return new ArrayList<>(res);
    }
}

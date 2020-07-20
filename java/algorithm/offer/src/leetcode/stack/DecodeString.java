package leetcode.stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @program: offer
 * @description: 字符串解密
 *  输入：s = "3[a]2[bc]"
 *  输出："aaabcbc"
 *  输入：s = "3[a2[c]]"
 *  输出："accaccacc"
 * @author: xjh
 * @create: 2020-06-13 08:52
 **/
public class DecodeString {
    private static DecodeString decodeString=new DecodeString();

    public static void main(String[] args) {
        System.out.println(decodeString.decodeString("2[a3[b]]"));
    }

    /**
    * @Description: 栈 时间：n
     * 符号区分:[：数字入栈，字符串入栈，初始化数字与字符串,数字,字符,]：根据栈顶字符与上一个res计算字符串在于栈顶字符串拼接
     * 如 2[a3[b]]  2入栈，a入栈，3入栈，b存储在res，遇到]符号，res与数字栈顶循环得到tem（bbb），tem与字符栈顶拼接abbb存入res，
     * 又遇到]符号，res（abbb）与栈顶数字循环，abbbabbb放入res，返回res，结束。
     * 变量：res：StringBuilder记录[与下一个[之间的字符串  multi：记录[与下一个[之间的与循环次数  遇到下一个[就要进入栈
     * stack_multi/stack_res：记录上面多个[与[之间的字符串与循环次数
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/13
    */
    public String decodeString1(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();   //记录数字的栈
        LinkedList<String> stack_res = new LinkedList<>();  //记录字符串的栈
        for(Character c : s.toCharArray()) {
            if(c == '[') {
                stack_multi.addLast(multi);  //遇到[时，前面数字入栈
                stack_res.addLast(res.toString()); //上一个[与这个[中间的字符串入栈
                multi = 0;  //重新初始化数字和字符串
                res = new StringBuilder();
            }
            else if(c == ']') {  //res与栈顶循环在与字符栈顶拼接放入res
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for(int i = 0; i < cur_multi; i++) tmp.append(res);
                res = new StringBuilder(stack_res.removeLast() + tmp);  //把此[]与他外面一层的[]中字符拼接放入res
            }
            else if(c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
            else res.append(c);  //字符进入res
        }
        return res.toString();
    }

    
    /**
    * @Description: 递归  深度优先遍历  时间：n
     * 变量：i遍历string的指针，res：存放拼接的字符串  s:整个字符串
     * 递归退出：遇到]符号，返回此时i指针指向的位置（即退出时]符号的下标）  递归：遇到[符号，递推退出时把退出的字符串与当前的数字multi循环
     *
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/13
    */
    public String decodeString(String s) {
        return dfs(s, 0)[0];
    }
    private String[] dfs(String s, int i) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        while(i < s.length()) {
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
            else if(s.charAt(i) == '[') {  //遇到[符号进入递归，把s和[的位置+1传入
                String[] tmp = dfs(s, i + 1);  //递归返回[]中的字符
                i = Integer.parseInt(tmp[0]);
                while(multi > 0) {
                    res.append(tmp[1]);
                    multi--;
                }
            }
            else if(s.charAt(i) == ']')  //遇到]符号结束递归，返回计算结果
                return new String[] { String.valueOf(i), res.toString() };   //i：此]符号的下标  res：[]中的字符串
            else
                res.append(String.valueOf(s.charAt(i)));
            i++;
        }
        return new String[] { res.toString() };
    }
}

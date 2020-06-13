package leetcode.tencentfifty;

import java.util.Stack;

/**
 * @program: offer
 * @description:有效的括号
 * @author: xjh
 * @create: 2020-06-06 15:10
 **/
public class IsValid {

    /**
    * @Description: 用stack  可以用哈希表存储括号对简化代码
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/6
    */
    public boolean isValid(String s) {
        if (s==null||"".equals(s)){
            return true;
        }
        char[] chars=s.toCharArray();
        Stack<Character> stack=new Stack();
        stack.push(chars[0]);
        for (int i=1;i<chars.length;i++){
            if (chars[i]=='('||chars[i]=='['||chars[i]=='{'){
                stack.push(chars[i]);
                continue;
            }
            if (stack.isEmpty()){
                return false;
            }
            switch(chars[i]){
                case ')':
                    if (stack.pop()!='(') return false;
                    break;
                case ']':
                    if (stack.pop()!='[') return false;
                    break;
                case '}':
                    if (stack.pop()!='{') return false;
                    break;
            }
        }
        return stack.isEmpty();
    }
}

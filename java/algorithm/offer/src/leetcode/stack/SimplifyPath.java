package leetcode.stack;

import java.util.Stack;

/**
 * @program: offer
 * @description: 简化路径
 * @author: xjh
 * @create: 2020-06-12 13:57
 **/
public class SimplifyPath {
    private static SimplifyPath simplifyPath=new SimplifyPath();

    public static void main(String[] args) {
        System.out.println(simplifyPath.simplifyPath("/a//b////c/d//././/.."));
    }


    /**
    * @Description: 栈  输入..后出栈，后面的输入会影响前面已经输入的，所以用栈
     * 路径用/分割后分为四种：.  ..  "路径"  ""
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/12
    */
    public String simplifyPath(String path) {
        path=path.replaceAll("/+","/");
        String[] pathArray=path.split("/");
        Stack<String> stack=new Stack();
        for (int i=0;i<pathArray.length;i++){
            if (".".equals(pathArray[i])){
                continue;
            }else if ("..".equals(pathArray[i])){
                if (!stack.isEmpty()){
                    stack.pop();
                }
            }else if(!"".equals(pathArray[i])){
                stack.push(pathArray[i]);
            }
        }
        if (stack.isEmpty()){
            return "/";
        }
        StringBuilder string=new StringBuilder();
        for (int i=0;i<stack.size();i++){
            string.append("/"+stack.elementAt(i));

        }
        return string.toString();
    }
}

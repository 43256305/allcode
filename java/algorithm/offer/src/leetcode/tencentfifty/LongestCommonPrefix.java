package leetcode.tencentfifty;

/**
 * @program: offer
 * @description:最长公共前缀
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * @author: xjh
 * @create: 2020-06-05 19:23
 **/
public class LongestCommonPrefix {

    /**
    * @Description: 暴力法  时间复杂度 m*n
     * i（0，i<strs[0].length）代表当前正在匹配的字符  j(1,j<strs.length)代表尚未匹配的字符串
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/5
    */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length==0){
            return "";
        }
        char[] cArray=new char[strs[0].length()];
        OUT:for (int i=0;i<strs[0].length();i++){
            char c=strs[0].charAt(i);
            for (int j=1;j<strs.length;j++){
                if (strs[j].length()-1<i||strs[j].charAt(i)!=c){
                    break OUT;
                }
            }
            cArray[i]=c;
        }
        return new String(cArray).trim();
    }
}

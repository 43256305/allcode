import java.util.ArrayList;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-05-29 08:38
 **/
public class Solution {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("123455xbabad"));
    }

    public static String longestPalindrome(String s) {
        if (s==null||s.length()==0){
            return "";
        }
        ArrayList<String> list=new ArrayList<>();
        for (int i=0;i<s.length()-1;i++){
            int j=1+i;
            String temp;
            String reverse;
            while (j<s.length()){
                temp=s.substring(i,j+1);
                reverse=new StringBuffer(temp).reverse().toString();
                if (temp.equals(reverse)){
                    list.add(temp);
                }
                j++;
            }
        }
        if (list.size()!=0){
            int size=0;
            int index=-1;
            for (int i=0;i<list.size();i++){
                if (list.get(i).length()>size){
                    size=list.get(i).length();
                    index=i;
                }
            }
            return list.get(index);
        }else {
            return s.substring(0,1);  //如果字符串长度不是0，且没有回文子串，则输出第一个
        }
    }
}

package leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: offer
 * @description: 电话号码的字母组合
 * @author: xjh
 * @create: 2020-06-11 22:05
 **/
public class LetterCombinations {
    private static LetterCombinations combinations=new LetterCombinations();

    public static void main(String[] args) {
        System.out.println(combinations.letterCombinations("23"));
    }

    
    /**
    * @Description: 暴力法
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/11
    */
    public List<String> letterCombinations(String digits) {
        if (digits==null||"".equals(digits)){
            return new ArrayList<>();
        }
        Map<Character,String> map=new HashMap<Character, String>(){
            {
                put('2',"abc");
                put('3',"def");
                put('4',"ghi");
                put('5',"jkl");
                put('6',"mno");
                put('7',"pqrs");
                put('8',"tuv");
                put('9',"wxyz");
            }
        };
        ArrayList<String> listOld=new ArrayList<>();
        for (int i=0;i<digits.length();i++){   //把数字全部翻译成字母存入old
            listOld.add(map.get(digits.charAt(i)));
        }
        ArrayList<String> list=new ArrayList<>();
        char[] temp=listOld.get(listOld.size()-1).toCharArray();
        for (int i=0;i<temp.length;i++){   //从old的最后一个字符串开始，分解成字符存入list
            list.add(String.valueOf(temp[i]));
        }
        for (int i=listOld.size()-2;i>=0;i--){  //从倒数第二个开始遍历old
            temp=listOld.get(i).toCharArray();
            for (int j=list.size()-1;j>=0;j--){  //从list最后一个开始，与temp每一个字符结合，删除选中的最后一个，再把结合的新字符串加入list
                //如temp=a b c  list=d e f  选出f 删除f af,bf,cf加入list  选出e 删除e ae，be，ce加入list
                String str=list.get(j);
                list.remove(j);
                for (int k=0;k<temp.length;k++){
                    list.add(String.valueOf(temp[k])+str);
                }
            }
        }
        return list;
    }
}

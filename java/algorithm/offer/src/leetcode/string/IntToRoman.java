package leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: offer
 * @description: 整数转罗马数字
 * @author: xjh
 * @create: 2020-06-12 10:46
 **/
public class IntToRoman {
    private static IntToRoman roman=new IntToRoman();

    public static void main(String[] args) {
        System.out.println(roman.intToRoman2(9));
    }

    public String intToRoman(int num) {
        int div=1;
        Map<Integer,String> map=new HashMap<Integer, String>(){
            {
                put(1,"I");
                put(5,"V");
                put(10,"X");
                put(50,"L");
                put(100,"C");
                put(500,"D");
                put(1000,"M");
            }
        };

        while (num / div >= 10) div*=10;
        StringBuilder result=new StringBuilder();
        int carry;
        while (div>=1){
            carry=num/div;
            if (carry==9){
                result.append(map.get(div));
                result.append(map.get(div*10));
            }else if(carry==4){
                result.append(map.get(div));
                result.append(map.get(div*5));
            }else{
                if (carry>=5){  //5,6,7,8
                    result.append(map.get(div*5));
                    for (int i=0;i<carry-5;i++){
                        result.append(map.get(div));
                    }
                }else{   //1,2,3
                    for (int i=0;i<carry;i++){
                        result.append(map.get(div));
                    }
                }
            }
            num %= div;
            div /= 10;
        }
        return result.toString();
    }


    /**
    * @Description: 贪心法
     * 一共有13种，从1000-1，先处理大的，大的处理完了，再处理小的
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/12
    */
    public String intToRoman1(int num) {
        // 按照阿拉伯数字的大小降序排列，这是贪心选择思想
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};  //length为13
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};  //此数组与nums数组对应

        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (index < 13) {
            // 特别注意：这里是等号
            while (num >= nums[index]) {  //逐个判断是否大于1000 900 500等
                // 注意：这里是等于号，表示尽量使用大的"面值"
                stringBuilder.append(romans[index]);
                num -= nums[index];
            }
            index++;
        }
        return stringBuilder.toString();
    }


    /**
    * @Description: 根据特点：千位，百位，十位的数都可以列举出来，所以我们只需要选出num的千位，百位，十位对应就行
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/12
    */
    public String intToRoman2(int num) {
        String table[][]={
                {"","I","II","III","IV","V","VI","VII","VIII","IX"},  //1-9
                {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},  //10-90
                {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},  //100-900
                {"","M","MM","MMM"} //1000-3000
        };
        StringBuilder roman = new StringBuilder();
        roman.append(table[3][num/1000]);  //处理千位
        num%=1000;
        roman.append(table[2][num/100]);  //处理百位
        num%=100;
        roman.append(table[1][num/10]);
        num%=10;
        roman.append(table[0][num]);
        return roman.toString();
    }
}

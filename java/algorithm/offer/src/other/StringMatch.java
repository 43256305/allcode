package other;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: offer
 * @description: 字符串模式匹配
 * @author: xjh
 * @create: 2020-03-26 11:07
 **/
public class StringMatch {

    /**
    * @Description: 时间复杂度：平均：n  最坏：mn->最坏时：文本全是0，模式为10000，每次都要比较m次，每次移动一个
     * 变量：i，k
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/26
    */
    public static int horspoolMatching(char[] text,char[] model){
        int i=model.length-1;   //i代表text中开始匹配的位置
        Map map=shiftTable(model);
        while (i<text.length){  //一直匹配到text的最后一个字符
            int k=0;   //k代表已经匹配成功的长度
            while (k<model.length&&text[i-k]==model[model.length-1-k]){  //k不能等于model.length，因为m-1-k会等于-1
                k++;
            }
            if (k==model.length){  //匹配成功  匹配成功后k==m
                return i-model.length+1;   //返回匹配到的起始位置，如text=ABCD  model=ABC  返回0
            }else{
                i=i+(int)map.get(text[i]);  //文本中开始匹配的字符（i），在模式中距离最后一位的距离
            }
        }
        return -1;
    }

    /**
    * @Description: 构造一个匹配大写字母文本的移动表  目的：移动表中显示了遇到每一个字符时，模式应该移动的距离（如模式中没有的字符，移动模式的
     * 长度，模式中有的字符，移动此字符到最后一个字符的距离）
    * @Param: cArray是模式
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/26
    */
    public static Map<Character,Integer> shiftTable(char[] cArray){
        Map<Character,Integer> map=new HashMap<>(26);
        for (int i=0;i<26;i++){
            map.put((char)('A'+i),cArray.length);
        }

        //当有新的字符加入表中时
        map.put('_',cArray.length);

        //模式的最后一个不用管（因为计算的都是模式的前m-1个字符距离m个字符的距离），为模式长度
        for (int i=0;i<cArray.length-1;i++){
            map.put(cArray[i],cArray.length-1-i);   //ABCD中，A的距离为：m-1-i=4-1-0=3
        }
        return map;
    }

    public static void main(String[] args) {
        //从0开始
        System.out.println(horspoolMatching("ABCDEFGHIJK".toCharArray(),"PPP".toCharArray()));  //：-1
        System.out.println(horspoolMatching("KKSDFHLSFASDF".toCharArray(),"KK".toCharArray()));  //：0
        //下面出现了新字符_
        System.out.println(horspoolMatching("BESS_KNEW_ABOUT_BAOBABS".toCharArray(),"BAOBAB".toCharArray()));
    }
}

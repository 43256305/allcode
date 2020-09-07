package leetcode.bitoperation;

/**
 * @program: offer
 * @description:只出现一次的数字
 * @author: xjh
 * @create: 2020-06-09 09:24
 **/
public class SingleNumber {

    public static void main(String[] args) {
        //因为异或满足结合律与交换律，所以下面的式子化为：(10^10)^(5^5)^7=0^0^7=7
        System.out.println(10^5^10^5^7);   //:7

        int seenOnce=0,seenTwice=0,num=10;
        for (int i=0;i<3;i++){
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }
        System.out.println(seenOnce);  //:0
    }

    /**
    * @Description: 位运算
     * 题目：给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 任何数与自己异或都是0，任何数与0异或都是自己（1^0=1  0^0=0  1^1=0）
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/9
    */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    /**
    * @Description: 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/9
    */
    public int singleNumber1(int[] nums) {
        int seenOnce = 0, seenTwice = 0;

        for (int num : nums) {
            //第一次碰到num，把num加入once，第二次，把num从once中移除，放入twice，第三次，从twice中移除，once与twice变为0
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }

        return seenOnce;
    }
}

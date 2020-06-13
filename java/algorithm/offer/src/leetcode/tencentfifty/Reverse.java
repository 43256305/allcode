package leetcode.tencentfifty;

/**
 * @program: offer
 * @description:整数反转  反转后超出整数的最大值和最小值则返回0
 * @author: xjh
 * @create: 2020-06-04 19:52
 **/
public class Reverse {

    /**
    * @Description:
     * reverse>Integer.MAX_VALUE/10判断还没加上个位时reverse是否大于最大值，reverse==Integer.MAX_VALUE/10&&pop>7判断加上个位后是否大于最大值
     * 其实||后面的可以不加，因为只要去掉个位后不大于最大值，加上个位后也不可能大于最大值，因为x本身也是整数，x的位数达到10位时，首位只可能是1或2
     * 而当x的位数没达到10位时，它的反转reverse自然也不可能溢出
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/4
    */
    public int reverse(int x) {
        int reverse=0;
        while (x!=0){
            int pop=x%10;  //%10是取尾巴

            //判断去掉个位是否溢出（为什么要判断去掉个位呢？因为最后一步就是给reverse加上个位，加上个位后再判断是否溢出已经没有意义了，所以
            // 要在他加上个位之前判断）
            if (reverse>Integer.MAX_VALUE/10||(reverse==Integer.MAX_VALUE/10&&pop>7)) return 0;
            if (reverse<Integer.MIN_VALUE/10 || (reverse==Integer.MIN_VALUE/10&&pop<-8)) return 0;

            reverse=reverse*10+pop;
            x/=10;
        }
        return reverse;
    }

    public int reverse1(int x) {
        int ans = 0;
        while (x != 0) {

            //判断是否溢出，其实就是上面的解法颠倒了  reverse>INTMAX/10-->reverse*10>INTMAX-->reverse*10/10!=ans
            if ((ans * 10) / 10 != ans) {
                ans = 0;
                break;
            }

            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans;
    }
}

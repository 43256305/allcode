package leetcode.inorder;

/**
 * @program: offer
 * @description: 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * @author: xjh
 * @create: 2020-07-04 21:57
 **/
public class Divide {

    /**
    * @Description:
     * 原理：把除法变为加法（或减法），先记录下两个数的除法的结果的正负值，以免后面计算时区分正负，再把两个数都变为long型正数，最后
     * 用divisor一直相加，直到小于或等于dividend的值，相加的次数，就是商
     * 特例：dividend==0，divisor==1，divisor==-1（变为相反数，注意MAX边界值）
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/7/4
    */
    public int divide(int dividend, int divisor) {
        if(dividend == 0) return 0;
        if(divisor == 1) return dividend;
        if(divisor == -1){
            if(dividend>Integer.MIN_VALUE) return -dividend;
            return Integer.MAX_VALUE;
        }
        long a = dividend;
        long b = divisor;
        int sign = 1;   //记录结果的正负
        if((a > 0 && b < 0) || (a < 0 && b > 0)){
            sign = -1;
        }
        //把a，b都变为正数
        a = a > 0 ? a : -a;
        b = b > 0 ? b : -b;
        long res = div(a,b);
        return sign == -1 ? (int)-res:(int)res;
    }
    int div(long a, long b) {   //原理：如a=50，b=5，则count=2 b=10，count=4 b=20，count=8 b=40
        // 最后return语句中a=10 b=5，再次计算 count=2 b=10  则count=8+2=10
        if (a < b) return 0;
        long count = 1;
        long tb = b; // 在后面的代码中不更新b
        while ((tb + tb) <= a) {
            count = count + count; // 最小解翻倍
            tb = tb + tb; // 当前测试的值也翻倍
        }
        return (int)(count + div(a - tb, b));
    }
}

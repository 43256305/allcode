package leetcode.bitoperation;

/**
 * @program: offer
 * @description: 位1的位数  计算数字n的二进制中1的个数
 * @author: xjh
 * @create: 2020-06-09 16:18
 **/
public class HammingWeight {

    public int hammingWeight(int n) {
        int sum=0;
        while (n!=0){
//            if ((n&1)!=0) sum++;
            sum+=(n&1);  //少了比较操作
            n=n>>>1;
        }
        return sum;
    }

    /**
     * @Description: 判断n的二进制中1的个数
     * @Param:
     * @return:
     * @Author: xjh
     * @Date: 2020/6/9
     */
    public int countA(int n){
        int res = 0;
        while(n != 0){
            n &= (n - 1);   //每次和比自己小1的数与操作，那么该数的二进制表示最后一个为1位上的1将将会被抹去如1000100&1000011=1000000
            //与自己的补码相与，则得到的是保留最右边的一个1的数
            res++;
        }
        return res;
    }
}

package leetcode.dynamicprogramming;

/**
 * @program: offer
 * @description:买卖股票的最佳时机
 * @author: xjh
 * @create: 2020-06-02 10:50
 **/
public class MaxProfit {
    /**
    * @Description:   prices[5]-prices[1]=prices[2]-prices[1]+prices[3]-prices[2]+prices[4]-prices[3]+prices[5]-prices[4]
     * 所以先声明一个数组p，p[1]=prices[1]-prices[0],p[2]=prices[2]-prices[1],p[n]=prices[n]-prices[n-1]
     * 要求两点相减最大，可以转换为求p数组最大子序列和。
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/2
    */
    public int maxProfit(int[] prices) {
        if (prices.length<2){
            return 0;
        }
        int last=0,profit=0;
        for (int i=0;i<prices.length-1;i++){
            last=Math.max(0,last+prices[i+1]-prices[i]);   //判断是否为负数，为负数则last取0，重新开始计算（last相当于最大子序和的sum）
            profit=Math.max(profit,last);
        }

        return profit;
    }

    /**
    * @Description: 暴力法
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/2
    */
    public int maxProfit1(int[] prices){
        if (prices.length<2){
            return 0;
        }
        int max=0;
        for (int i=0;i<prices.length-1;i++){
            for (int j=i+1;j<prices.length;j++){
                if (prices[j]-prices[i]>max){
                    max=prices[j]-prices[i];
                }
            }
        }
        return max;
    }
}

package leetcode.tencentfifty;

/**
 * @program: offer
 * @description:买卖股票的最佳时机2
 * @author: xjh
 * @create: 2020-06-05 19:50
 **/
public class MaxProfit1 {
    public int maxProfit(int[] prices) {
        if (prices==null||prices.length==0||prices.length==1){
            return 0;
        }
        int sum=0;
        for (int i=1;i<prices.length;i++){
            int temp;
            temp=prices[i]-prices[i-1];  //把差值为正数的加起来就为最大值
            if (temp>0){
                sum+=temp;
            }
        }
        return sum;
    }
}

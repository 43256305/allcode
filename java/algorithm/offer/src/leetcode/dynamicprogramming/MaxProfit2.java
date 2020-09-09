package leetcode.dynamicprogramming;

/**
 * @program: offer
 * @description: 买卖股票的最佳时机
 * 可以买卖两次
 * @author: xjh
 * @create: 2020-09-07 11:04
 **/
public class MaxProfit2 {

    public class Solution {

        /**
        * @Description: 动态规划
         * dp[i][j]代表第i天的获取的最大利润，i为天数，j为操作（见下面）
         * 状态转移方程dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]+/-prices[i])（注意max后一个j值）   +/-取决于当天是否持有股票（即是否当天卖出/买入股票）
         * 解释：dp[i][j]当天可以选择不操作，或者买入卖出股票（上一个状态买入/卖出转入新的状态），两种
         * 初始值（第0天）：不买入：dp[0][0]=0（j==0的dp值永远为0，因为j==0代表还没有操作，利润当然为0）   买入：dp[0][1]=-prices[0]
         * 填表顺序：i从1开始，j一直增大，即从第二行开始向右填表，一直到最后一行
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/9/7
        */
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len < 2) {
                return 0;
            }

            // dp[i][j] ，i表示天数，j表示状态
            // j = 0：什么都不操作
            // j = 1：第 1 次买入一支股票   持有 -
            // j = 2：第 1 次卖出一支股票   不持有 +
            // j = 3：第 2 次买入一支股票    持有 -
            // j = 4：第 2 次卖出一支股票    不持有 +

            // 初始化
            int[][] dp = new int[len][5];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];  //买入一次，则减去当天的prices
            dp[0][2] = 0;
            dp[0][3] = -prices[0];   //也算买入一次
            dp[0][4] = 0;

            // 状态转移只有 2 种情况：
            // 情况 1：什么都不做
            // 情况 2：由上一个状态转移过来（即减或者加prices[i]，当前为持有，则为-（今天买入了），当前为不持有，则为+（今天卖出了））
            for (int i = 1; i < len; i++) {
                // j = 0 的值永远是 0
                dp[i][0] = 0;
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);  //注意max方法中，后一个j值为上一个状态的转移
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
                dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
                dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
            }
            // 最大值只发生在不持股的时候，因此来源有 3 个：j = 0 ,j = 2, j = 4
            return Math.max(0, Math.max(dp[len - 1][2], dp[len - 1][4]));
        }
    }

}

package leetcode.dynamicprogramming;

/**
 * @program: offer
 * @description: 零钱兑换  https://juejin.im/post/5b0a8e0f51882538b2592963
 * @author: xjh
 * @create: 2020-06-02 20:00
 **/
public class CoinChange {

    /**
    * @Description:动态规划    参考背包问题
     * 原理：进行amount轮，每轮都遍历coins数组，算出此轮选取不同硬币时最小的硬币个数，即memo[i-coins[j]]进行coins.length次比较，
     * 每次更新j的值，得出这几轮中最小min  递推关系：memo[i]=min(memeo[i-coins[0]],memo[i-coins[1]],....,memo[i-coins[coins.length-1]])+1
     * 变量：i为要兑换的总额，j为硬币个数
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/2
    */
    public int coinChange(int[] coins, int amount) {
        // 自底向上的动态规划
        if(coins.length == 0){
            return -1;
        }

        // memo[n]的值： 表示的凑成总金额为n所需的最少的硬币个数
        int[] memo = new int[amount+1];
        memo[0] = 0;
        for(int i = 1; i <= amount;i++){
            //min的作用：当前面值（i）的最小硬币个数。（每次都试coins.length次，得出取那个coin时min最小，初始为memo[i-coins[0]]）
            int min = Integer.MAX_VALUE;
            for(int j = 0;j < coins.length;j++){
                //coins[j]表示此时这枚硬币面值，i是此时要兑换的金钱总额，i-coins[j]>=0表示需要找零的面额大于这枚硬币面值
                //memo[i-coins[j]]表示不包括此时这枚硬币时需要的硬币个数
                if(i - coins[j] >= 0 && memo[i-coins[j]] < min){
                    min = memo[i-coins[j]] + 1;
                }
            }
            memo[i] = min;//当没有进入j循环时，可能memo[i]=Integer.MAX_VALUE
        }

        return memo[amount] == Integer.MAX_VALUE ? -1 : memo[amount];
    }
}

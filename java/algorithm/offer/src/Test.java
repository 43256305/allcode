import leetcode.TreeNode;

import java.util.*;

/**
 * @program: offer
 * @description:
 * @author: xjh
 * @create: 2020-06-15 08:41
 **/
public class Test {
    private static Test test = new Test();
    public static void main(String[] args) {
//        System.out.println(solution());
//        System.out.println(Integer.MAX_VALUE+1);
        Solution solution = new Solution();
//        int[] a = {3,6,5,1,8};
//        solution.maxSumDivThree(a);
        solution.inputMult();
    }

    static class Solution {

        public void inputSingle(){
            Scanner in = new Scanner(System.in);
            while (in.hasNextInt()){
                int a = in.nextInt();
                int b = in.nextInt();
                System.out.println(a + " " + b);
            }
        }

        public void inputMult(){
           
        }

        public
        int maxSumDivThree(int[] nums) {
            int[] dp = {0, 0, 0};

            for (int i = 0; i < nums.length; ++i) {
                int mod = nums[i] % 3;

                int a = dp[(3 + 0 - mod) % 3];
                int b = dp[(3 + 1 - mod) % 3];
                int c = dp[(3 + 2 - mod) % 3];

                if (a !=0 || mod == 0) dp[0] = Math.max(dp[0], a + nums[i]);
                if (b !=0 || mod == 1) dp[1] = Math.max(dp[1], b + nums[i]);
                if (c !=0 || mod == 2) dp[2] = Math.max(dp[2], c + nums[i]);
            }

            return dp[0];
        }
    };

    public static int solution(){
        Scanner in = new Scanner(System.in);
        int num = Integer.valueOf(in.nextLine());
        int[] prices = new int[num];
        String[] temp = in.nextLine().split(" ");
        for (int i = 0;i < num;i ++){
            prices[i] = Integer.valueOf(temp[i]);
        }
        int money = Integer.valueOf(in.nextLine());

        return backTrack(-1,money,prices);
    }

    public static int backTrack(int i,int money,int[] prices){
        if (money == 0){
            return 1;
        }

        if (money < 0){
            return 0;
        }


        i++;
        for (;i<prices.length;i++){
            if (backTrack(i,money-prices[i],prices)==1){
                return 1;
            }
        }

        return 0;
    }

}

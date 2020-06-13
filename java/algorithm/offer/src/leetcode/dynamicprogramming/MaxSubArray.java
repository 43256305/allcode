package leetcode.dynamicprogramming;

/**
 * @program: offer
 * @description:最大子序和
 *
 * @author: xjh
 * @create: 2020-06-01 17:33
 **/
public class MaxSubArray {

    public static void main(String[] args) {
        int[] array={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxArraySubArray(array));
    }

    /**
    * @Description: 动态规划   ans代表最大子序列和，sum为局部最大自序和   求出当前的sum值，当sum为正数时，则sum需要保留，当sum为非正时，需要舍弃
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/2
    */
    public static int maxArraySubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for(int num: nums) {
            if(sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}

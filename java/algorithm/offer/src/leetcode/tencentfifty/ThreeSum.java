package leetcode.tencentfifty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: offer
 * @description: 三数之和
 * @author: xjh
 * @create: 2020-06-05 20:18
 **/
public class ThreeSum {

    public static void main(String[] args) {
        int[] array={0,1,2};
        System.out.println(threeSumClosest1(array,3));
    }

    /**
    * @Description:双指针法（双指针一般是同时处理两个或三个值是运用）   时间：n^2
     * 1.便于去重  2.双指针可以根据sum值与0相比调整sum的大小（sum需要增加则l指针++，需要减少，则r指针--）
     *变量：left(i+1,left<right) right(n-1,right>left)
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/5
    */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(nums == null || len < 3) return ans;
        Arrays.sort(nums); // 排序   （排序后可以排除从大于0开始的，可以排除重复值）
        for (int i = 0; i < len ; i++) {
            if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;   //如果sum<0说明sum需要变大，则L++
                else if (sum > 0) R--;   //如果sum>0说明sum需要变小，则R--
            }
        }
        return ans;
    }

    /**
    * @Description: 找出三数之和中最接近target的，输出三数之和
     *
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/6
    */
    public static int threeSumClosest1(int[] nums, int target) {
        int min=Integer.MAX_VALUE;
        int len = nums.length;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len ; i++) {
            int L = i+1;
            int R = len-1;
            while(L < R){
                int ans = nums[i] + nums[L] + nums[R]-target;
                if (Math.abs(min)>Math.abs(ans)){
                    min=ans;
                }
                if (ans < 0) L++;
                else if (ans > 0) R--;
                else return target;
            }
        }
        return min+target;
    }


    /**
    * @Description: 四数之和  双指针
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/8
    */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len=nums.length;
        Arrays.sort(nums);
        List list=new ArrayList();
        int left;
        int right;
        for (int i=0;i<len-3;i++){
            if (i>0 && nums[i]==nums[i-1]) continue;  //去重
            for (int j=i+1;j<len-2;j++){
                if (j>i+1 && nums[j]==nums[j-1]) continue;  //去重
                left=j+1;
                right=len-1;
                while (left<right){
                    int sum=nums[i]+nums[j]+nums[left]+nums[right];
                    if (sum<target){
                        left++;
                    }else if (sum>target){
                        right--;
                    }else{
                        list.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        while (left<right&&nums[left]==nums[left+1]) left++;  //去重
                        while (left < right&&nums[right]==nums[right-1]) right--;
                        left++;
                        right--;
                    }
                }
            }
        }
        return list;
    }
}

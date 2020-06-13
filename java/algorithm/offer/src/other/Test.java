package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: offer
 * @description:
 * @author: xjh
 * @create: 2020-06-08 11:30
 **/
public class Test {
    private static Test test=new Test();
    public static void main(String[] args) {
        int[] array={1,0,-1,0,-2,2};
        test.fourSum(array,0);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len=nums.length;
        Arrays.sort(nums);
        List list=new ArrayList();
        int left;
        int right;
        for (int i=0;i<len-3;i++){
            for (int j=i+1;j<len-2;j++){
                if (nums[j]==nums[j-1]){
                    j++;
                    continue;
                }
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
                        break;
                    }
                }
            }
        }
        return list;
    }
}

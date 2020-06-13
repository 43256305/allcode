package leetcode.doublepointer;

/**
 * @program: offer
 * @description:
 * @author: xjh
 * @create: 2020-06-08 19:19
 **/
public class RemoveElement {

    /**
     * @Description: 移除元素（移除数组中与val相同的元素）  双指针
     * @Param:
     * @return:
     * @Author: xjh
     * @Date: 2020/6/8
     */
    public int removeElement(int[] nums, int val) {
        if (nums.length==0){
            return 0;
        }
        int n=nums.length;
        int left=0;   //一旦left与val相等，则于right交换
        int right=nums.length-1;
        while (right>=0&&nums[right]==val){  //保证right从第一个非val数字开始，right始终指向非val数字
            right--;
            n--;
        }
        for (;left<n;left++){
            if (nums[left]==val){
                nums[left]=nums[right];
                right--;
                n--;
                while (nums[right]==val){
                    right--;
                    n--;
                }
            }
        }
        return n;
    }
}

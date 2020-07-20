package leetcode.inorder;

/**
 * @program: offer
 * @description: 在排序数组中查找元素的第一个和最后一个位置
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * @author: xjh
 * @create: 2020-07-18 22:52
 **/
public class SearchRange {
    /**
    * @Description:
     * 原理：因为是排序数组，所以用二分查找到目标值后，开始值与结束值一定在目标值的附近，且开始值到目标值都是target
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/7/18
    */
    public int[] searchRange(int[] nums, int target) {
        int[] res ={-1,-1};
        if (nums.length == 0){
            return res;
        }
        int left = 0;
        int right = nums.length - 1;

        while (left <= right){
            int mid = (left + right)>>1;
            if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else{
                findStartAndFinal(nums,mid,target,res);
                break;
            }
        }

        return res;
    }

    /**
    * @Description: 从mid开始，向数组的两边扩展，知道找到第一个与最后一个target的位置
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/7/18
    */
    private void findStartAndFinal(int[] nums,int mid,int target,int[] res){
        int finalIndex = mid;
        int startIndex = mid;
        while (finalIndex + 1 < nums.length && nums[finalIndex + 1] == target) finalIndex++;
        while (startIndex - 1 > -1 && nums[startIndex - 1] == target) startIndex--;
        res[0] = startIndex;
        res[1] = finalIndex;
    }
}

package leetcode.inorder;

/**
 * @program: offer
 * @description: 搜索旋转排序数组
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * @author: xjh
 * @create: 2020-06-22 19:50
 **/
public class Search {

    /**
    * @Description:
     * mid值可能位于两个位置：前一个排序数或者后一个排序数组，位于前一个排序数组时，判断target是否在前一个排序数组，mid位于后一个
     * 排序数组时，判断target是否位于后一个排序数组   那么，怎么判断target位于哪个排序数组呢？难道我们要找出两个排序数组的分界线
     * 吗？不用的，既然已知mid位于哪个排序数组，我们直接把target和mid比较即可，而mid与0位置比较得出位于哪个排序数组
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/7/4
    */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return target == nums[0] ? 0:-1;
        int l = 0 , r = n - 1;
        while (l <= r){
            int mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            if (nums[0] <= nums[mid]){  //mid位于前一段有序数组
                if (target >= nums[l] && target < nums[mid]){  //target位于前一段有序数组
                    r = mid - 1;
                }else {
                    l = mid + 1;
                }
            }else { //mid位于后一段有序数组
                if (target > nums[mid] && target <= nums[r]){  //target位于后一段有序数组
                    l = mid + 1;
                }else{
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}

package leetcode;

/**
 * @program: offer
 * @description: 多数元素
 * @author: xjh
 * @create: 2020-06-09 15:05
 **/
public class MajorityElement {

    /**
    * @Description: 分治法  时间复杂度 nlogn  我们把数组分为两半，其中有一半的众数一定为最后的我们要找的多数值，另一半可能是，也可能不是
     * 如果不是，计算两边众数的个数，谁的个数多，谁就是众数
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/9
    */
    public int majorityElement1(int[] nums) {
        return majorityElementRec(nums, 0, nums.length-1);
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi-lo)/2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid+1, hi);

        // 如果两边的众数都一样，则这个数肯定是众数，直接返回
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }



    /**
    * @Description: 找出数组中个数大于n/2的元素     投票法  时间复杂度：n
     * num与candidate相等，则count++，反之则count--，count为0时更新candidate的值
     * 如果我们设一个值value，他明确是众数的个数，遍历时，与众数相等，则value++，反之value--，当candidate为众数时，count值与value相同
     * 当candidate不为众数时，count与value为相反数（此时value为负数）（value最后一定大于0，所以count最后也一定大于0，并且此时candidate为众数）
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/9
    */
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}

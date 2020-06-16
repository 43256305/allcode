package leetcode.inorder;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: offer
 * @description: 两数之和
 * @author: xjh
 * @create: 2020-06-16 09:54
 **/
public class TwoSum {

    class Solution1{
        /**
        * @Description: 两遍哈希表  时间：n
         * 把数组的所有的值，下标存储在哈希表中，再次遍历数组，计算出target与数组某个元素的差值，在哈希表中寻找这个差值containsKey()
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/16
        */
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);  //以value,index的形式存储
            }
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement) && map.get(complement) != i) {  //因为题目规定找出的两个数下标不能相同，所以不能等于i
                    return new int[] { i, map.get(complement) };
                }
            }
            throw new IllegalArgumentException("No two sum solution");
        }

        /**
        * @Description: 一遍哈希表
         * 一边加入哈希表，一边在哈希表中查找是否存在差值元素，但是查找的值只是以前加入哈希表的值，后面未加入的值就查找不到了，怎么办呢？
         * 其实这是没有问题的，如 nums=1,2 target=3 遍历1时，查找2显然查找不出，遍历2时，就能查找出1了。
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/16
        */
        public int[] twoSum1(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[] { map.get(complement), i };
                }
                map.put(nums[i], i);
            }
            throw new IllegalArgumentException("No two sum solution");
        }
    }
}

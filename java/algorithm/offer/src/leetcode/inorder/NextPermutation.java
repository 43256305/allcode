package leetcode.inorder;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: offer
 * @description: 下一个排列
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * @author: xjh
 * @create: 2020-06-22 16:01
 **/
public class NextPermutation {

    class Solution1 {
        /**
        * @Description: 可以把数组看成两段，前一段不管，后一段是一个倒序数组，倒序数组中前面的一个元素比倒序数组的起始元素小，我们只需要
         * 把这个小的元素替换成倒序数组中第一个比他大的元素，然后倒序数组改为顺序数组就行了
         * 如 x x x 3 10 5 2 1   其中10到1为倒序数组，3为第一个比倒序数组小的元素，我们只需要把3替换成5，然后换成顺序就可以，结果为
         * x x x 5 1 2 3 10
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/22
        */
        public void nextPermutation(int[] nums) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i + 1] <= nums[i]) {//i指向第一个小于倒序数组的元素，如上面例子的3
                i--;
            }
            if (i >= 0) {     //如果整个数组都是倒序的，则i=-1，直接执行下面的reverse函数，如length=3，i=1，减两次，i=-1
                int j = nums.length - 1;
                while (j >= 0 && nums[j] <= nums[i]) {  //从后面找出第一个大于i的元素，然后与i交换位置，退出循环，颠倒i后面的数组
                    //如X X X 3 10 5 2 1  5与3交换位置，然后从10开始往后颠倒数组
                    j--;
                }
                swap(nums, i, j);
            }
            reverse(nums, i + 1);
        }

        private void reverse(int[] nums, int start) {
            int i = start, j = nums.length - 1;
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    class Solution2 {
        public void nextPermutation(int[] nums) {
            if ( nums.length <= 1){
                return;
            }
            boolean flag = true;
            int i = nums.length-1;
            Queue<Integer> queue = new LinkedList<Integer>();
            int q = Integer.MAX_VALUE;
            int k = 0;
            for (;i > 0;i--){
                queue.offer(nums[i]);   //队列中记录倒序的数据
                if (nums[i] > nums[i-1]){
                    flag = false;
                    q = nums[i-1];   //第一个非倒序的元素
                    k = i - 1;   //记录位置
                    break;
                }
            }
            if (flag){  //说明数组是倒序的
                int right = nums.length-1;
                int left =0 ;
                while (left < right){
                    swap(nums,left,right);
                    left ++;
                    right --;
                }
            }else {
                while (!queue.isEmpty()){
                    if (q >= queue.peek()){
                        nums[i] = queue.poll();
                        i++;
                    }else {
                        nums[k] = queue.poll();
                        nums[i] = q;
                        i++;
                        q = Integer.MAX_VALUE;
                    }
                }
            }
        }

        public void swap(int[] nums,int i,int j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}

package leetcode.divideandconquer;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @program: offer
 * @description:数组中第k个最大元素 (即倒数第k大元素)
 * @author: xjh
 * @create: 2020-06-15 14:32
 **/
public class FindKthLargest {
    private static FindKthLargest largest=new FindKthLargest();
    public static void main(String[] args) {
        int[] arr={3,4,6,1,8,9};
        FindKthLargest.Solution3 solution3=largest.new Solution3();
        solution3.findKthLargest(arr,3);

    }

    class Solution1{
        /**
         * @Description: 运用快排中的分区方法，选出枢轴元素，判断枢轴元素是否大于n-k，大于n-k则再次对左边的数组分区，直到找到枢轴元素等于n-k
         * @Param:
         * @return:
         * @Author: xjh
         * @Date: 2020/6/15
         */
        public int findKthLargest(int[] nums, int k) {
            //(n-1)-i+1=k  倒数第k个元素  i=n-k  如长度为6时的倒数第2个，就是 6-2=4位置上的元素
            //快捷记忆：倒数第k个元素在数组中的位置为n-k
            int kIndex=nums.length-k;
            int s = partition(nums,0,nums.length-1);
            while (kIndex != s){
                if (s < kIndex){
                    s = partition(nums,s+1,nums.length-1);
                }else{
                    s = partition(nums,0,s-1);
                }
            }

            return nums[kIndex];
        }

        public int partition(int[] nums,int low,int high){
            int temp = nums[low];
            while (low < high){
                while (low < high && nums[high] >= temp) high--;

                if (low < high){
                    nums[low] = nums[high];
                    low++;
                }

                while (low < high && nums[low] <= temp) low++;

                if (low < high){
                    nums[high] = nums[low];
                    high--;
                }
            }

            nums[high] = temp;
            return high;
        }
    }


    class Solution2{

        int [] nums;

        /**
        * @Description: 分治+递归
         * 原理与Solution1差不多，partition方法由于枢轴元素是随机的，而不是固定在low，所以使用了一个指针向放置在最右边的枢轴逼近
         * （总之要遍历数组，每个元素都要与枢轴比较，只不过一个是从两边逼近，一个是从一遍逼近），quickselect变为了递归   当遇到极端测试用例
         * 时（顺序数组或倒序数组），Solution2比1快很多
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/15
        */
        public int findKthLargest(int[] nums, int k) {
            this.nums = nums;
            int size = nums.length;
            return quickselect(0, size - 1, size - k);
        }

        public int quickselect(int left, int right, int k_smallest) {
            if (left == right)
                return this.nums[left];

            // 选取随机的枢轴元素  （Solution1总是选取第一个）
            Random random_num = new Random();
            int pivot_index = left + random_num.nextInt(right - left);

            pivot_index = partition(left, right, pivot_index);

            if (k_smallest == pivot_index)
                return this.nums[k_smallest];
            else if (k_smallest < pivot_index)
                return quickselect(left, pivot_index - 1, k_smallest);
            return quickselect(pivot_index + 1, right, k_smallest);
        }

        /**
        * @Description: 双指针，store_index指针始终指向比枢轴元素小的位置的后一个元素，i指针则遍历数组，一旦i指向的元素比枢轴元素小，则与
         * store_index交换位置，store_index指针向前移动，最后枢轴元素与store_index交互，形成枢轴左边都比他小，右边都比他大
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/15
        */
        public int partition(int left, int right, int pivot_index) {
            int pivot = this.nums[pivot_index];
            // 枢轴元素与最后一个元素交换位置
            swap(pivot_index, right);
            int store_index = left;  //总是指向比枢轴元素小的位置的后一个元素

            // 从起始位置开始比较把比pivot小的元素全部放在前面
            for (int i = left; i <= right; i++) {
                if (this.nums[i] < pivot) {
                    swap(store_index, i);
                    store_index++;
                }
            }

            // 3. move pivot to its final place
            swap(store_index, right);

            return store_index;
        }

        public void swap(int a, int b) {
            int tmp = this.nums[a];
            this.nums[a] = this.nums[b];
            this.nums[b] = tmp;
        }


    }

    class Solution3{
        
        /**
        * @Description: 堆，优先队列
         * 原理：把数组元素都加入优先队列（优先队列是从小到大排列），然后直接出队前面len-k个元素，最后的顶点元素就是要查找的值
         * 如 1 2 3 4 5 查找第2大的元素 5-2=3，前三个：1 2 3 出队，4即是第k大的元素
        * @Param: 
        * @return: 
        * @Author: xjh
        * @Date: 2020/6/15
        */
        public int findKthLargest(int[] nums, int k) {
            int len = nums.length;
            // 使用一个含有 len 个元素的最小堆，默认是最小堆，可以不写 lambda 表达式：(a, b) -> a - b
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(len, (a, b) -> a - b);  //a<b则返回一个小于0的数
            for (int i = 0; i < len; i++) {
                minHeap.add(nums[i]);
            }
            for (int i = 0; i < len - k; i++) {
                minHeap.poll();
            }
            return minHeap.peek();
        }

        /**
        * @Description: 从小到大的优先队列，有元素就加入，但是队列只保存k个元素（最大的那k个元素），超过k就出队（最小的元素出队）
         * 最后一定是最大的k个元素在队列中，直接返回队列中的第一个元素就是第k大的元素
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/15
        */
        public int findKthLargest1(int[] nums, int k) {
            // init heap 'the smallest element first'
            PriorityQueue<Integer> heap =
                    new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

            // keep k largest elements in the heap
            for (int n: nums) {
                heap.add(n);
                if (heap.size() > k)
                    heap.poll();
            }

            // output
            return heap.poll();
        }
    }
}

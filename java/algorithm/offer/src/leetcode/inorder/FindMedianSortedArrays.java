package leetcode.inorder;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @program: offer
 * @description: 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组nums1 和nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为O(log(m + n))。
 * 你可以假设nums1和nums2不会同时为空。
 *
 *nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 *
 * @author: xjh
 * @create: 2020-06-16 10:50
 **/
public class FindMedianSortedArrays {
    private static FindMedianSortedArrays medianSortedArrays = new FindMedianSortedArrays();
    public static void main(String[] args) {
        FindMedianSortedArrays.Solution2 solution2 = medianSortedArrays.new Solution2();
        int[] num1 = {1,3,5,7};
        int[] num2 = {1,2,3,4,5,6,7,8,9,10};
        solution2.findMedianSortedArrays(num1,num2);
    }

    class Solution1{

        /**
        * @Description: 两个顺序数组合并一个  时间：n/2
         * 先算出两个数组的中位数下标，然后把两个数组合并为一个数组，一旦k的值等于中位数下标，则说明不用合并了，已经找到了
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/16
        */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len1=nums1.length;
            int len2=nums2.length;
            int minIndex = (len1 + len2) / 2;
            int[] num = new int[len1+len2];
            int k=0;
            int i=0,j=0;
            while (i<len1 && j < len2){
                if (nums1[i] < nums2[j]){
                    num[k]=nums1[i];
                    if (k == minIndex){
                        if (((len1 + len2)&1) == 1){  // k=(len1+len2)/2即总长度/2 寻找中位数时，如果总长度为奇数：直接返回k  如3 返回1
                            return num[k];
                        }else {
                            return (num[k]+num[k-1])/2.0;   //如果总长度是偶数：返回k和k的前一个除以2  如4，返回 2加1除以2
                        }
                    }
                    k++;
                    i++;
                }else{
                    num[k]=nums2[j];
                    if (k == minIndex){
                        if (((len1 + len2)&1) == 1){
                            return num[k];
                        }else {
                            return (num[k]+num[k-1])/2.0;
                        }
                    }
                    k++;
                    j++;
                }
            }

            while (i < len1){
                num[k]=nums1[i];
                if (k == minIndex){
                    if (((len1 + len2)&1) == 1){
                        return num[k];
                    }else {
                        return (num[k]+num[k-1])/2.0;
                    }
                }
                k++;
                i++;
            }

            while (j < len2){
                num[k]=nums2[j];
                if (k == minIndex){
                    if (((len1 + len2)&1) == 1){
                        return num[k];
                    }else {
                        return (num[k]+num[k-1])/2.0;
                    }
                }
                k++;
                j++;
            }
            return 0;
        }
    }

    class Solution2{
        
        /**
        * @Description: 二分查找   时间：logn
         *开始时，k=totallen/2   把求两个已排序的数组的中位数变为求第k小的元素，k有两个取值，当为奇数时，两个k一样，偶数时，两个k相差1
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/16
        */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n = nums1.length;
            int m = nums2.length;
            int left = (n + m + 1) / 2;   //如果n+m=14 中位数为：left=7 right=8相加  如果n+m=13  中位数为：left=7 right=7  7为第7大的数，而不是下标
            int right = (n + m + 2) / 2;
            //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
            return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
        }

        /**
        * @Description: 退出条件：一个数组剩余的长度已经变为了0（直接在第二个数组中取），k变为了1（哪个小取哪个）
         * 选择条件：取2/k位置的元素比较，哪个小去掉哪个的前2/k个元素
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/6/19
        */
        private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
            int len1 = end1 - start1 + 1;
            int len2 = end2 - start2 + 1;
            //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
            if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
            if (len1 == 0) return nums2[start2 + k - 1];   //如果nums1已经没有数字了，则直接在nums2里面找
            //快捷记忆：数组从0开始，求倒数第k个元素的下标：n-k  求第k个元素的位置：k-1  （上面由于开始元素位置会改变，所以也要加上开始下标）

            if (k == 1) return Math.min(nums1[start1], nums2[start2]);  //第1小的元素当然是第一个

            //在把k变为它的1/2，即求第k/2小的元素，因为求得是下标所以要-1
            int i = start1 + Math.min(len1, k / 2) - 1;  //如果k/2越界，则直接取末尾元素
            int j = start2 + Math.min(len2, k / 2) - 1;

            if (nums1[i] > nums2[j]) {
                //k/2位置上第二个数组小于第一个，则第二个数组的起始位置变为原来的k/2位置+1（即k/2位置前的元素都抛弃），并且k改变
                return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
                //原来是求第k大的元素，现在已经减少了k/2，那么k也要变化，如原来求第7小元素，去除2/k=3后，k为4
                //上式中 j-start2+1为去除的元素个数（j为2/k的下标，减去起始下标并加1后就是需要去掉的元素个数）
            }
            else {
                //与上面相反
                return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
            }
        }
    }
}

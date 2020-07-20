package leetcode.doublepointer;

/**
 * @program: offer
 * @description: 移除元素  给你一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * @author: xjh
 * @create: 2020-06-08 19:19
 **/
public class RemoveElement {

    /**
     * @Description: 移除元素（移除数组中与val相同的元素）  双指针
     * 原理：val=2  nums= 2 2 3 2 2 4 2 首先，right指向最后一个，循环前进后，right指向4（因为要保证right始终指向非val元素），left指向2
     * ，显然，2是val，所以把right赋值给left，4 2 3 2 2 4 2 right再循环前进，指向3，left++指向2，又是val，right赋值给left，4 3 3 2 2 4 2
     * 此时n已经变为了2，left为1，循环结束。
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
        for (;left<n;left++){  //left从0开始，一旦nums[left]==val就要把right赋值给left，right--，n--，right再循环前进直到不是val
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

    /**
    * @Description: 双指针
     * i从0开始，j也是从0开始，遍历j，一旦j不为val，则赋值给i且i++，继续遍历j直到结束。最后返回i即可。
     * 如 val=2 nums=1 2 3  i=0,j=0赋值且i++，i=1，j=1不赋值，i=1，j=2赋值且i++：1 3 3 最后返回2
     * 当出现重复元素时，i指向的就是无效元素
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/17
    */
    public int removeElement1(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}

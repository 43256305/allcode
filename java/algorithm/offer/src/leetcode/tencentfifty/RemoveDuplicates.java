package leetcode.tencentfifty;

/**
 * @program: offer
 * @description:
 * @author: xjh
 * @create: 2020-06-04 15:20
 **/
public class RemoveDuplicates {

    /**
    * @Description: i始终指向已经选出的非重复数组的最后一个（一旦有一个不相等的元素，就i++，赋值给i，与i相等的元素就不会赋值给i了）
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/4
    */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {   //i先+在赋值，使得就算数组元素完全不一样，也不会原改变数组，因为当没有相同的时，i+1=j
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    /**
    * @Description: 删除排序数组中的重复项2，使得每个元素最多出现两次(暴力法)  时间复杂度n^2
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/9
    */
    public int[] remElement(int[] arr, int index) {

        //index后面的元素全部向前移动一位，以便移除index上的元素
        for (int i = index + 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }

        return arr;
    }

    public int removeDuplicates1(int[] nums) {

        // Initialize the counter and the array index.
        int i = 1, count = 1, length = nums.length;

        while (i < length) {

            //
            // If the current element is a duplicate,
            // increment the count.
            //
            if (nums[i] == nums[i - 1]) {

                count++;

                //
                // If the count is more than 2, this is an unwanted duplicate element
                // and hence we remove it from the array.
                //
                if (count > 2) {

                    this.remElement(nums, i);

                    //
                    // Note that we have to decrement the array index value to
                    // keep it consistent with the size of the array.
                    //
                    i--;

                    //
                    // Since we have a fixed size array and we can't actually
                    // remove an element, we reduce the length of the array as
                    // well.
                    //
                    length--;
                }
            } else {

                //
                // Reset the count since we encountered a different element
                // than the previous one.
                //
                count = 1;
            }

            // Move on to the next element in the array
            i++;
        }

        return length;
    }

    /**
    * @Description: 双指针   时间复杂度：n
     * i（1）指向有效元素，j（1，j<n）遍历数组
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/9
    */
    public int removeDuplicates2(int[] nums) {

        int i = 1, count = 1;    //i从1开始，因为0肯定是有效元素

        int j = 1;  //j从1开始，因为j要与他的前一个元素比较

        for (; j < nums.length; j++) {

            //
            // If the current element is a duplicate, increment the count.
            //
            if (nums[j] == nums[j - 1]) {

                count++;

            } else {

                //
                // Reset the count since we encountered a different element
                // than the previous one.
                //
                count = 1;
            }

            
            if (count <= 2) {
                nums[i++] = nums[j];   //只有count小于等于2时，i才会++  i于j中间的元素都是需要丢弃的
            }
        }
        return i;
    }
}

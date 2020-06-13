package other;

import java.util.Arrays;

/**
 * @program: offer
 * @description: 二分查找
 * @author: xjh
 * @create: 2020-03-11 15:38
 **/
public class BinaryFind {
    public static void main(String[] args) {
        int[] array={1,2,3,4,6,9,11,55,88,100};
        System.out.println(findValue(array,100));  //:true
        int[][] array3={{1,2,3,4},{5,6,7,8}};
        System.out.println(findValueTwoDime(array3,3));  //:true

        int[] array1={1,2,3,4,5,6,7,8};
        insertionSearch(array1,7);

    }

    /**
    * @Description: 一维数组(排好序)折半查找  while(low<=high) midVal>key:high=mid-1  midVal<key:low=mid+1  mid==key:true
     * 时间复杂度：logn（一直除以2）
     * //二分查找四个变量low，high，mid，midval   为什么while（low<=high)有等号呢？如0（low） 1 2（high）查找2，low=mid+1  0 1 2(low,high),2+2/2=2查找成功
     * //所以往往要查找的值不是在low与high的中间，而是在low与high重叠时找到，重叠时就是等于。
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/3/11
    */
    public static boolean findValue(int[] array,int key){
        int high=array.length-1;  //别忘了减1
        int low=0;
        while (low <= high) {
            //右移运算，相当于除以2
            int mid = (low + high) >>> 1;
            long midVal = array[mid];

            if (midVal < key)
                low = mid + 1;  //为什么要+1？因为mid上的值肯定小于key，所以mid上的值已经可以排除了
            else if (midVal > key)
                high = mid - 1;
            else
                return true; // key found
        }
        return false;
    }

    public static boolean findValueTwoDime(int[][] array,int key){
        int high=array.length*array[0].length-1;   //从0开始的30个数，最后一个下标就是29
        int low=0;
        int mid;
        int midValue;
        int x;
        int y;
        while (high>=low){
            mid=(high+low) >>> 1;
            //二维数组中，从左往右数知道他是第num个数，求x，y，只需要把num=num-1（如果num从0开始数就不用减），num/列就是x，num%列就是y
            //如果从上往下数，则除的是行
            x=mid/array[0].length;
            y=mid%array[0].length;
            midValue=array[x][y];
            if (midValue>key){
                high=mid-1;
            }else if (midValue<key){
                low=mid+1;
            }else{
                return true;
            }
        }
        return false;
    }

    /**
    * @Description:插入查找（自适应性二分查找）  二分查找总是从中间开始，但是如果我们要找的值在最左边或最右边，就没必要从中间开始，而加上了
     * 下面的这个公式，就会自适应从哪里合适的位置开始查找。如1，2，3，4，5，6，7，8查找7   mid=0+6/7*7=6，而array[6]正好等于7，查找成功
     * 对于表长较大，而关键字分布又比较均匀的查找表来说，插值查找算法的平均性能比折半查找要好的多。反之，数组中如果分布非常不均匀，那么插值查找未必是很合适的选择。
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/5/29
    */
    public static void insertionSearch(int[] array,int key){
        int low=0;
        int high=array.length-1;
        int mid;
        int midVal;
        while (low<=high){
            mid=(int)(low+(key*1.0-array[low])/(array[high]-array[low])*(high-low));  //注意：key-array[low]永远小于等于array[high]-array[low]
            //所以如果按整数计算，这个分数永远为0或1，失去了价值，而乘1.0则把他变为了一个double
            midVal=array[mid];
            if (key<midVal){
                high=mid-1;
            }else if (key>midVal){
                low=mid+1;
            }else {
                System.out.println("位置为："+mid);
                return;
            }
        }
    }
}

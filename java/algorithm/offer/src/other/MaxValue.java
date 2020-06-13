package other;

/**
 * @program: offer
 * @description: 给定一个无序数组，包含正数、负数和0，要求从中找出3个数的乘积，使得乘积最大，要求时间复杂度：O(n)，空间复杂度：O(1)
 * @author: xjh
 * @create: 2020-03-11 15:01
 **/
public class MaxValue {
    /**
    * @Description: 最大*（次大*第三大） 或者是 最大*（最小*次小）   三种情况：1.全部负数：最大相乘  2.全正：最大相乘  3.有正有负：最大*（最小*最小）
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/11
    */
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);  //:2147483647
        int[] array={1,2,3,4,2,9,3,6,8};
        int[] array1={-9,-1,-3,-9,-7};
        int[] array2={-1,4,5,8,9,2};
        int[] array3={-1,1};
        int[] array4={1,2};
        System.out.println("-----------------------------");
        System.out.println(getMax(array4));  //:2
        System.out.println(getMax(array3));  //:-1
        System.out.println(getMax(array2));  //:360
        System.out.println(getMax(array1));  //:-21
        System.out.println(getMax(array));   //:432
    }
    
    /**
    * @Description: 用O(n)时间复杂度就计算出了一个数组中最大的几个数，最小的几个数
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/11
    */
    public static int getMax(int[] array){
        if (array.length==0){
            System.out.println("请重新输入");
            return 0;
        }
        if (array.length<=3&&array.length>=1){
            int max=1;
            for (int i=0;i<array.length;i++){
                max=max*array[i];
            }
            return max;
        }
        //只要数组有3个值以上，下面的5个值就都会更新，不会是原来的最大(小)整数
        int min_fir=Integer.MAX_VALUE,min_sec=Integer.MIN_VALUE;
        int max_fir=Integer.MIN_VALUE,max_sec=Integer.MIN_VALUE,max_thi=Integer.MIN_VALUE;
        //找出数组中最大的三个值，最小的两个值
        for (int i=0;i<array.length;i++){
            if (array[i]<min_fir){
                min_sec=min_fir;  //一直让min_sec保持第二小状态
                min_fir=array[i];
            }else if (array[i]<min_sec){
                //如果这个数比最小值大，却比第二小小，则赋值给第二小
                min_sec=array[i];
            }
            if (array[i]>max_fir){
                max_thi=max_sec;
                max_sec=max_fir;
                max_fir=array[i];
            }else if (array[i]>max_sec){
                max_thi=max_sec;
                max_sec=array[i];
            }else if (array[i]>max_thi){
                max_thi=array[i];
            }
        }
        return Math.max(max_fir*max_sec*max_thi,max_fir*min_fir*min_sec);
    }
    
}

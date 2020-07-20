package other;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @program: offer
 * @description: 快速排序
 * @author: xjh
 * @create: 2020-03-25 15:05
 **/
public class QucikSort {
    public static void main(String[] args) {
        int[] array1={3,5,1,9,0,2};
        int[] array2={1,0};
        int[] array3={3,2,3,1,2,4,5,5,6};
        quickSort(array1,0,array1.length-1);
        quickSort(array2,0,array2.length-1);
        quickSort(array3,0,array3.length-1);
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
        System.out.println(Arrays.toString(array3));

        int[] array={3,5,7,9,1,2};
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));

        //对字符串排序
        String s="EXAMPLE";
        byte[] b=s.getBytes();
        int[] array4=new int[b.length];
        for (int i=0;i<b.length;i++){
            array4[i]=b[i];
        }
        quickSort(array4,0,b.length-1);
        for (int i=0;i<array4.length;i++){
            b[i]=(byte)array4[i];
        }
        System.out.println(new String(b));

    }


    /**
    * @Description: 数组每一次排好一个元素，然后把排好的元素左和边的数组排序
     * 最坏情况，每次划分都是分为两个数组，一个数组长度为0，另一个为n-1（即已经排好序的数组，如1 2 3 4 5，每一轮划分j都要遍历到第一个元素）
     * 一共进行n此划分，时间为：n^2
     * 最好情况：每次划分都把数组平均分为两份 (下面递推关系中，n每次划分的时间，2M(n/2)为下面两次递归需要的时间)
     * M(n)=n+2M(n/2)=2n+4M(n/4)=3n+8M(n/8)=n*logn+NM(1)
     * 变量start，end
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/25
    */
    public static void quickSort(int[] array,int start,int end){  //因为是递归，所以也要指定start和end，要不然第二轮的时候就不知道开始结束了
        if (start<end){  //当start=end时，说明子数组就一个元素，不用在分割了      注意是if，不是while，递归怎么会和循环一起使用呢。
            int s;
            s=partition(array,start,end);   //s位置的已经排好序了
            quickSort(array,start,s-1);
            quickSort(array,s+1,end);
        }
    }

    /**
    * @Description: 函数目的：把temp放在，temp左边都小于他，右边都大于他的位置上：j，也就是说
     * j已经不用排序了，继续排被j分割出的两个子数组
     * 变量  temp(基准值，即起始位置)，i，j
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/25
    */
    private static int partition(int[] arr, int i, int j) {
        int temp = arr[i];  //给基准位置挖一个坑
        while (j > i) {
            // 找出一个比基准数小的数 （因为最后要形成基准的左边都比基准小，所以放到基准位置上的元素一定比基准小，所以从后面开始找）
            while (temp <= arr[j] && i < j) {
                --j;
            }
            // 当基准数大于了 arr[j]，则填坑（填坑后形成一个新坑，j遍历时填i位置的坑）
            if (i < j) {
                arr[i] = arr[j];  //填到i的位置
                ++i;  //为什么时i++？因为i位置上的坑已经被填了，所以i要前进一位
            }
            // 现在是 arr[j] 需要填坑了 （i遍历时，填j位置的坑）
            while (temp >= arr[i] && i < j) {
                ++i;
            }
            if (i < j) {
                arr[j] = arr[i];
                --j;
            }
        }
        arr[j] = temp;  //最后退出循环时，i一定等于j，因为上面每一个语句都有判断i<j，所以i一等于j，就会退出
        return j;
    }

    public static void swap(int[] array,int x,int y){
        int temp=array[x];
        array[x]=array[y];
        array[y]=temp;
    }

    /**
    * @Description: 三数取中
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/15
    */
    private static int partitionThree(int[] arr, int left, int right) {
        // 采用三数中值分割法
        int mid = left + (right - left) / 2;
        // 保证左端较小
        if (arr[left] > arr[right])
            swap(arr, left, right);
        // 保证中间较小
        if (arr[mid] > arr[right])
            swap(arr, mid, right);
        // 保证中间最小，左右最大
        if (arr[mid] > arr[left])
            swap(arr, left, mid);
        int pivot = arr[left];   //把左边的数当作基准值（左边比右边小，比中间大）
        while (right > left) {
            // 先判断基准数和后面的数依次比较
            while (pivot <= arr[right] && left < right) {
                --right;
            }
            // 当基准数大于了 arr[right]，则填坑
            if (left < right) {
                arr[left] = arr[right];
                ++left;
            }
            // 现在是 arr[right] 需要填坑了
            while (pivot >= arr[left] && left < right) {
                ++left;
            }
            if (left < right) {
                arr[right] = arr[left];
                --right;
            }
        }
        arr[left] = pivot;
        return left;
    }
}

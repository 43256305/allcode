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
//        int[] array1={3,5,1,9,0,2,6};
//        int[] array2={1,0};
//        int[] array3={0};
//        quickSort(array1,0,array1.length-1);
//        quickSort(array2,0,array2.length-1);
//        quickSort(array3,0,array3.length-1);
//        System.out.println(Arrays.toString(array1));
//        System.out.println(Arrays.toString(array2));
//        System.out.println(Arrays.toString(array3));

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
     * 变量start，end
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/25
    */
    public static  void quickSort(int[] array,int start,int end){  //因为是递归，所以也要指定start和end，要不然第二轮的时候就不知道开始结束了
        if (start<end){  //当start=end时，说明子数组就一个元素，不用在分割了      注意是if，不是while，递归怎么会和循环一起使用呢。
            int s;
            s=partition(array,start,end);   //s位置的已经排好序了
            quickSort(array,start,s-1);
            quickSort(array,s+1,end);
        }
    }

    /**
    * @Description: 函数目的：把array[start]放在，start左边都小于array[start]，右边都大于array[start]的位置上：j，也就是说
     * array[j]已经不用排序了，继续排被j分割出的两个子数组
     * 变量i，j
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/25
    */
    public static int partition(int[] array,int start,int end){   //start,end是子数组的开头和结束下标
        int i=start+1;
        int j=end;
        //上面的if和下面的循环都不能等于，因为每一轮的任务就是找出一个数，既然就是一个数，就不需要找了。
        while (i<j){   //找出每一个比array[start]大的元素（i），与比array[start]小的元素(j)，i与j交换位置，最后把array[start]放在j上
            while (i<end&&array[i]<=array[start]){  //等于的时候不需要交换位置，i继续前进（如果等于了，i在++就越界了）
                i++;
            }
            while (j>start&&array[j]>=array[start]){
                j--;
            }
            swap(array,i,j);
        }
        swap(array,i,j);  //撤销最后一次交换（循环停止时一定是i>=j，j指在比start大的元素，i指在比start小的元素上，所以一定要换回来，使得
        //i指向的元素比start大，j指向的元素比start小，这样start和j交换位置，才会导致j左边的都小于j）
        //为什么退出循环后需要再交换一次呢？（1.j与i最后交换一次后，i>=j了，退出循环，这时j一定大于start，但是j最后要与start交换位置，
        // 所以j最后一定要指向比start小的   2.j==start了，交换一次，退出循环，这时start已经被交换到了i的位置上，所以需要再交换回来）
        swap(array,start,j);
        return j;  //一个数组划分成两个数组，返回j，j位置上的元素一定在正确的位置上
    }

    public static void swap(int[] array,int x,int y){
        int temp=array[x];
        array[x]=array[y];
        array[y]=temp;
    }
}

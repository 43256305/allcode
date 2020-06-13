package binarytree;

import java.util.Arrays;

/**
 * @program: datastructure
 * @description:
 * @author: xjh
 * @create: 2020-05-26 17:22
 **/
public class Sort {
    public static void main(String[] args) {
        int[] array={2,1,5,7,9,3};
//        bubbleSort(array);
//        selectionSort(array);
//        insertSort(array);
        shellSort(array);
    }

    /**
    * @Description: 冒泡排序  每次比较j与j+1（所以j要小于n-1-i，不然越界）位置上的大小，每轮选出一个最大值放到数组最后面(所以要经过n-1轮，即为i<n-1)
     * 变量i（<n-1），j(<n-1-i)
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/5/26
    */
    public static void bubbleSort(int[] array){
        for (int i=0;i<array.length-1;i++){
            for (int j=0;j<array.length-1-i;j++){
                if (array[j]>array[j+1]){
                    swap(array,j,j+1);
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
    
    /**
    * @Description: 选择排序  同样选择n-1轮（所以i<n-1），每轮选出一个最小元素放在前面（所以前面0到i-1位都是排好序的）  冒泡排序是相邻元素比较
     * ，选择排序是前面的某个元素（即minIndex位置）与后面的所有元素比较（即j从i+1到最后）
     * 变量：i（0到n-2），j（i+1到n-1），minIndex（找到j小于）
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/5/26
    */
    public static void selectionSort(int[] array){
        int minIndex;   //只需要记录最小值的下表即可
        for (int i=0;i<array.length-1;i++){
            minIndex=i;
            for (int j=i+1;j<array.length;j++){
                if (array[minIndex]>array[j]){
                    minIndex=j;
                }
            }
            swap(array,i,minIndex);  //找出最小值后交换到i的位置上
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * @Description:插入排序 原理，前j=i-1已经排好序，如果j位大于i位，则把i位之前大于i位元素循环右移，直到找到正确位置插入i。  最优：n-1  最差：n(n-1)/2
     * 变量i（正要插入的元素下表  1到i<n），j（前一个与后一个  i-1到0），temp（正要插入的元素）
     * @Param:
     * @return:
     * @Author: xjh
     * @Date: 2020/3/9
     */
    public static void insertSort(int[] array){
        if (array.length<=1){
            return ;
        }
        int temp;
        for(int i=1;i<array.length;i++){
            temp=array[i];  //temp代表正要插入的元素
            int j=i-1;
            for (;j>=0&&array[j]>temp;j--){
                array[j+1]=array[j];
            }
            array[j+1]=temp;  //一定要j+1，j位置的值给了j+1，然后j在循环中减1，自然j+1需要新值
        }
        System.out.println(Arrays.toString(array));
    }

    /**
    * @Description: 希尔排序  原理：step为两个元素之间的间隔个数（初始为n/2然后重复除以2直到为1），间隔step的几个元素用插入排序
     * （即temp为i处的值，也就是要排序的元素）   （把step变为1就是插入排序）
     * 变量：step为间隔个数（n/2到n/4到n/8直到等于1）  i为要插入的元素（初始为step每次+1直到数组末尾）  temp为i位置上的元素值
     * j为已经排好序的的元素（从i-step开始每次都减step直到0）
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/5/26
    */
    public static void shellSort(int[] array){
        int step = array.length / 2;
        int j;
        int temp;
        while(step >= 1) {  //要经过n/2^k=1，k轮插入排序，所以有三重循环
            for(int i = step; i < array.length; i++) {
                temp = array[i];
                j = i - step;
                while(j >= 0 && array[j] > temp) {
                    array[j + step] = array[j];
                    j = j - step;    //注意j每次都是减step而不是1   如1 2 3 4 5   i=5时,step=1时,是5 3 1比较
                }
                array[j + step] = temp;
            }
            step /= 2;
        }
        System.out.println(Arrays.toString(array));
    }


    private static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}

package binarytree;

import java.util.Arrays;

/**
 * @program: datastructure
 * @description:
 * @author: xjh
 * @create: 2020-05-26 20:16
 **/
public class MergeSort {
    public static void main(String[] args) {
        int[] array={3,6,1,8,9,3,5,0,2};
        mergeSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }

    /**
     * @Description:归并排序   把一个数组从中间不断划分，划分为每个数组只有两个值，然后运用排好序的两个数组归并为一个数组的算法合并为一个数组
     * 关键思想：如果一个数组只有一个值，我们认为他已经排好序了。
     * 变量：low，high，mid(因为需要分为两个数组,所以需要mid)
     * @Param:
     * @return:
     * @Author: xjh
     * @Date: 2020/5/26
     */
    public static void mergeSort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {   //因为low<high所以不停划分最后一定会划分为只有两个值的数组去执行merge方法
            // 左边
            mergeSort(a, low, mid);
            // 右边
            mergeSort(a, mid + 1, high);
            // 左右归并
            merge(a, low, mid, high);
//            System.out.println(Arrays.toString(a));
        }
    }


    /**
    * @Description: 此方法作用：把两个已经排好序的数组（这两个数组就是一个数组从mid分开的）放入新数组中，再把新数组赋值进入老数组。
     * 为什么确定这两个数组就一定排好序了呢？归并排序最后一定会划分为只有两个值的数组，也就是左数组一个值，右数组一个值，相当于左右数组已经排好序了
     * 我们只需要比较这两个值谁大然后从小达到放入新数组就行。
     * 三个指针 i（指向第一个数组） j（指向第二个数组） k（指向新数组从0到k）  low，mid，high  新数组：temp[high-low+1]
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/5/26
    */
    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;  //k为新指针（从0到数组末尾）

        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] <= a[j]) {   //i在j的前面，当i与j元素相等时，我们先给i赋值，这样可以保证归并排序的稳定性。
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            a[k2 + low] = temp[k2];
        }
    }

}

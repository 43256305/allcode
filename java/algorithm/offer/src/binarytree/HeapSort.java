package binarytree;

import java.util.Arrays;

/**
 * @program: datastructure
 * @description: 堆排序  https://blog.csdn.net/zdp072/article/details/44227317
 * 堆插入数据都是放在数组的最后（从他的父节点开始调整，即第n/2开始调整一次），堆删除只能删除堆顶元素（为了便于重建堆，实际的操作是将最后一个
 * 数据的值赋给根结点，然后再从根结点开始进行一次从上向下的调整）
 * @author: xjh
 * @create: 2020-04-07 22:19
 **/
public class HeapSort {
    public static void main(String[] args) {
        int[] arr={50, 10, 90, 30, 70, 40, 80, 60, 20};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
    * @Description: 原理：首先从n/2开始构造大顶堆后，第一个元素比所有元素都大，我们只需要把这个元素放入数组的最后，然后重新开始调整一轮后
     * （调整起始位置始终为数组第一个，调整长度每轮减一，相当于每轮找出一个最大的），第一个元素又是最大的元素，然后把这个元素放入倒数第二个，
     * 循环，最后就是一个升序数组。
     * 变量i，代表需要调整的个数，从n-1到1
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/5/25
    */
    public static void heapSort(int[] arr){
        // 将待排序的序列构建成一个大顶堆   从length/2开始调整，直到0（index=0是堆顶，length/2才是第一个有子孙的节点）
        for (int i = arr.length / 2; i >= 0; i--){
            heapAdjust(arr, i, arr.length);
        }

        // 逐步将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为大顶堆   i!=0因为最后一个不用排自然是最小的
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i); // 将堆顶记录和当前未经排序子序列的最后一个记录交换
            heapAdjust(arr, 0, i); // 交换之后，需要重新检查堆是否符合大顶堆，不符合则要调整
//            为什么这里长度为i呢?因为每轮选出一个最大的排到末尾,所以已经排好的不用调整
        }
    }

    /**
    * @Description:从i开始调整，循环中i逐渐增加（i=child)，假如某个孩子节点大于父节点，则交换，并且下一轮调整上一轮与父节点交换位置的孩子与它的左右节点
     * 需要三个变量child与father，i代表father位置index，child代表最小节点index（每轮循环i变为它的子孙即child）
    * @Param: i:需要构建堆的根节点的序号，n:数组的长度
    * @return: 
    * @Author: xjh
    * @Date: 2020/4/7
    */
    public static void heapAdjust(int[] arr,int i,int n){
        int child;
        int father;
        for (father=arr[i];getLeft(i)<n;i=child){  //i=child：退出循环时，i（father位置）变为child
            child=getLeft(i);

            //如果左子树arr[child]小于右子树ar[child+1]，则需要比较右子树和父节点
            if (child!=n-1&&arr[child]<arr[child+1]){
                child++;  //child++变为右子树
            }

            // 如果父节点小于孩子结点，则需要交换（child经过上面的if，已经变为了father两个孩子中最大的元素的下标）
            if (father < arr[child]) {
                arr[i] = arr[child];  //father位值（i）上放置child位置上的元素（在调整中，不论经过几步father始终为第一轮的father，只需要最后把father放入到调整后的最下面即可）
            } else {
                return; // 大顶堆结构未被破坏，不需要调整
            }
        }
        arr[i]=father;  //i（循环最后i变为了child)换回原来的father     两个赋值语句,都是给i赋值
    }

    /**
    * @Description: 获取数组中某个元素的左节点的下标（因为是按层次生成的堆，所以0的左节点为1，1的左节点为3，3的左节点为7）
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/4/7
    */
    public static int getLeft(int i){
        return i*2+1;
    }

    // 交换元素位置
    private static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}

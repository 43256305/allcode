package other;

import java.util.Arrays;

/**
 * @program: offer
 * @description: 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * @author: xjh
 * @create: 2020-03-22 21:22
 **/
public class FindMin {

    
    /**
    * @Description: 数组为非递减，如｛0，1，1，1，1｝的旋转：｛1，0，1，1，1｝ 和 ｛1，1， 1，0，1｝  时间复杂度：n
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/22
    */
    public static int minNumberInRotateArray(int [] array) {
        if(array.length==0){
            return 0;
        }
        for (int i=0;i<array.length-1;i++){  //数组有两个递增数组（第一个递增数组全部大于或者等于第二个递增数组），所以一旦后面的小于前面的，必定是最小值
            if (array[i]>array[i+1]){
                return array[i+1];
            }
        }
        return -1;  //没找到
    }

    /**
    * @Description:严格递增数组如{1，2，3，4}的旋转数组{3，4，1，2}  时间复杂度：logn
     * 分析：因为是递增的（两段递增），所以二分查找
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/3/22
    */
    public static int binaryFin(int[] array){
        if(array.length==0){
            return 0;
        }
        int low=0;
        int high=array.length-1;
        int mid;
        int temp=array[0];   //temp一定大于第二个递增序列的所有元素
        while (low<high-1){  //循环下来，low永远在第一个递增序列，high永远在第二个递增序列，循环完成后，low一定在第一个序列的最后一个，high在第二个序列的第一个
//            如果low<high则循环不会停下来，如上面的解释，low最后会等于high-1（即成功找到），如果不是<high-1，则low=（low+high）>>>1=(high-1+high)>>>1=low死循环
            mid=(low+high)>>>1;
            if (array[mid]>temp){  //说明mid在第一个递增序列
                low=mid;
            }else{  //说明mid在第二个递增序列
                high=mid;
            }  //temp永远不会等于array[mid]，因为这是严格递增序列
        }
        return array[high];
    }

    public static void main(String[] args) {
        int[] array={7,8,10,1,2,3,5};
        int[] array1={3,1};
        System.out.println(binaryFin(array));  //:1
        System.out.println(binaryFin(array1));  //:1
    }
}

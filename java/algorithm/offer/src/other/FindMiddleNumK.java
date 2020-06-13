package other;

/**
 * @program: offer
 * @description: 寻找无序数组中第k大(k只能是中值，比如9length，k只能为5)的元素
 * @author: xjh
 * @create: 2020-03-12 13:57
 **/

public class FindMiddleNumK {

    public static void main(String[] args) {
        int[] array={4,1,10,9,7,12,8,2,15};
        int[] array1={1,5,8,9,2};
        int[] array2={3,2,1};
        int[] array3={1,3,5,7,9,9,3,2,6,7,4,5,7,9,10,11,12};
        int[] array4={1,2,3,4,5,6,7,8,9};
        System.out.println(getNumK(array,5));
        System.out.println(getNumK(array1,3));
        System.out.println(getNumK(array2,2));
        System.out.println(getNumK(array3,9));//:7
        System.out.println(getNumK(array4,5)); //:5
    }

    /**
    * @Description: s一直右移，把小于p的值放在s的左边，大于p放在右边，最后把p放在s上
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/3/22
    */
    public static int lomuPart(int[] array,int s,int l,int p){
        for (int i=l+1;i<array.length;i++){  //s从0开始增加，数组后面的值小于p则s++，把小于p的元素交换到s左边
            if (array[i]<p){
                s++;
                swap(array,s,i);  //s与i
            }
        }
        swap(array,l,s);  //p与s交换
        return s;
    }

    /**
    * @Description:如果s==k，找到（因为s左边的都小于p，右边的都大于p），s<k，说明k在s的右边，反之在左边
     * * 时间复杂度（递增数组pow(n,2)/8-n/2）：n^2
     * 变量：l（起点），s（分割点），p
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/3/22
    */
    public static int getNumK(int[] array,int k){
        if (array.length<k){
            System.out.println("第k个元素不存在");
            return 0;
        }
        int l=0;  //l为起点
        int s=0;
        int p=array[0];  //p为起点元素值
        s=lomuPart(array,s,l,p);
        while (true){
            if (s>k-1){  //取左边    要确定三个值，s=l（起始点，要么是最左边，要么是s+1），p
                s=l;
                p=array[l];
                s=lomuPart(array,s,l,p);
            }else if (s<k-1){  //取右边
                s++;
                l=s;
                p=array[l];
                s=lomuPart(array,s,l,p);
            }else{  //当s==k时，成功
                return array[s];
            }
        }

    }
    public static void swap(int[] array,int x,int y){
        int temp=array[x];
        array[x]=array[y];
        array[y]=temp;
    }

}

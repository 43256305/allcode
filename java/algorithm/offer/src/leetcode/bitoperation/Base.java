package leetcode.bitoperation;

/**
 * @program: offer
 * @description:
 * @author: xjh
 * @create: 2020-06-09 13:24
 **/
public class Base {
    private static Base base=new Base();

    public static void main(String[] args) {
        base.printTwoNum();
    }


    /**
    * @Description: 判断num奇偶（num末尾为1则一定是奇数，为0则是偶数）
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/9
    */
    public boolean isOdd(int num){
        return (num & 1) != 0;
    }


    /**
    * @Description: 判断num是否为2的整数次幂（2的整数次幂是只有一个位置是1，减1后变为后面全是1）
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/9
    */
    public boolean log2(int num){
        return (num & (num - 1)) == 0;
    }


    /**
    * @Description: 判断n的二进制中1的个数
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/9
    */
    public int countA(int n){
        int res = 0;
        while(n != 0){
            n &= (n - 1);   //每次和比自己小1的数与操作，那么该数的二进制表示最后一个为1位上的1将将会被抹去如1000100&1000011=1000000
            res++;
        }
        return res;
    }


    /**
    * @Description: 打印数组中只出现一次的两个数
     * 先找出两数异或二进制最后一个1，在根据这个1找出这两个数中此位上为1的元素。
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/9
    */
    public void printTwoNum(){
        int[] arr={1,2,3,4,3,1,2,5};
        int num=0;
        int one=0;  //one为要找的两个数中的一个

        for (int a:arr){
            num^=a;  //最后num是要找的两个数的异或  只要这两个数不相同，则一定会有一位二进制为1
        }

        int rightOne=num & (~num+1);  //一个数与自己的反码加1相与（补码），最后结果是保留这个数二进制的最后一个1，如11000&01000=01000

        for (int a:arr){
            //事实上下面的！=也可以换成==，结果one不同
            if ((rightOne & a)!=0){   //两个数中一定有一个数的rightOne位上为1，一个为0，为0的数就不会参与运算（虽然其他值也可能不参
                //与运算，但其他值参与不参与都不影响结果，因为其他值都是两个数要么一起参加，要么一起不参加）于是one就是rightOne位上为1的那个数
                one^=a;
            }
        }
        System.out.println(one+"  "+(one^rightOne));
    }

}

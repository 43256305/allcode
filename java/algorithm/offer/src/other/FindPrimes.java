package other;

import java.util.Arrays;

/**
 * @program: offer
 * @description:判断n是否为素数    如果大量需要素数，则可以构造一次素数表，多次使用
 * 注意0和1即不是素数也不是合数
 * @author: xjh
 * @create: 2020-04-02 11:38
 **/
public class FindPrimes {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        System.out.println(isPrimes4(1000000));
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        start=System.currentTimeMillis();
        System.out.println(isPrimes3(1000000));
        end=System.currentTimeMillis();
        System.out.println(end-start);
    }


    /**
    * @Description: 如果一个数x是素数，那么在整数范围[2,√x ]之间，找不到任何能整除x 的整数   时间复杂度为根号n
     * 求出n以内所有素数为n*sqrt（n）  因为要调用这个函数n次
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/4/2
    */
    public static boolean isPrimes1(int n){
        for (int i=2;i<Math.sqrt(n);i++){
            if (n%i==0){
                return false;
            }
        }
        return true;
    }

    /**
    * @Description: 这里用了*，而不是数学公式sqrt，一是sqrt是浮点运算速度慢，二是函数调用浪费时间，三是浮点运算不准确
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/4/2
    */
    public static boolean isPrimes2(int n){
        for (int i=2;i*i<=n;i++){
            if (n%i==0){
                return false;
            }
        }
        return true;
    }

    /**
    * @Description: 用筛选法，可以求出n以内的所有素数   时间复杂度：nloglogn
     * 变量：i（从2开始遍历），j（从i*i开始一直加i直到n设为素数）
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/4/2
    */
    public static boolean isPrimes3(int n){
        int[] array=new int[n+1];
        array[0]=1;//0不是素数
        array[1]=1;//1不是素数
        for (int i=2;i*i<=n;i++){
            if (array[i]==0){
                for (int j=i*i;j<=n;j=j+i){    //从i*i开始，依次+i
                    array[j]=1;   //标记为非素数
                }
            }
        }
//        System.out.println(Arrays.toString(array));
        if (array[n]==1){
            return false;
        }else return true;
    }

    /**
    * @Description: 前一种算法也会有重复的部分，如：15*2=30  5*6=30，这就筛选了两次
     * 而这种算法不会重复，理论上时间复杂度为：n
     * 原理：让每个合数只被其最小的质因数筛一次
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/4/2
    */
    public static boolean isPrimes4(int n){
        int[] array=new int[n+1];
        int[] p=new int[n];  //素数集合
        array[0]=1;
        array[1]=1;
        int index=0;   //记录素数的个数
        for(int i = 2; i <= n; i++) {
            if(array[i] == 0){
                p[index ++] = i;
            }
            //为什么j要<=index呢？因为数组p中比index大还没赋值   为什么i*p[j]<=n呢？因为我们筛选的就是array[i*p[j]]上的数，而array长度为n+1
            //i=2：4(2*2)  i=3:6(2*3) 9(3*3)  i=4:8(2*4)  i=5:10(2*5) 15(3*5) 25(5*5)   i=6:12 18 30
            //i从2到n，每次都筛选出[i*已知质数]位置上的元素，碰到i%质数为0，停止筛选
            //为什么要停止筛选呢？如i=4，只筛选出8，如果还筛选除了12，那么i=6时，又会筛选一次12，这就重复了
            //这就保证了所有2的倍数的数（4，6，12等）都只会被2筛选一次，而不会被3筛选
            for(int j = 0; j < index && i * p[j] <= n; j++) {
                array[i * p[j]] = 1;  //每个素数对应的i倍的合数筛去
                //下面的break让重复筛选不可能
                if(i % p[j] == 0)   //如果i%p[j]==0，则p[j]一定是i的最小质因数（因为p是递增的素数集合）
                    break;
            }
        }
//        System.out.println(Arrays.toString(array));
        if (array[n]==0) return true;
        else return false;
    }
}

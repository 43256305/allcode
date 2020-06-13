package leetcode.dynamicprogramming;

/**
 * @program: offer
 * @description: 爬楼梯
 * @author: xjh
 * @create: 2020-06-02 10:32
 **/
public class ClimbStairs {
    public static void main(String[] args) {

    }

    public static int climbStairs(int n) {
        if (n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        int num[] =new int[n+1];
        num[0]=0;
        num[1]=1;
        num[2]=2;
        for (int i=3;i<=n;i++){
            num[i]=num[i-1]+num[i-2];
        }
        return num[n];
    }

    public static int clibStairs1(int n){
        if (n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        int pre=2,prepre=1;
        for (int i=3;i<=n;i++){
            pre=pre+prepre;
            prepre=pre-prepre;
        }
        return pre;
    }
}

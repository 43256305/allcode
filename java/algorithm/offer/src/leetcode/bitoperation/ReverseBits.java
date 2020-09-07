package leetcode.bitoperation;

/**
 * @program: offer
 * @description:颠倒二进制数
 * @author: xjh
 * @create: 2020-06-09 15:55
 **/
public class ReverseBits {

    public static void main(String[] args) {
        int i=(int)Math.pow(2,31);
        System.out.println(Integer.toBinaryString(i)+"  "+Integer.toBinaryString(i).length());
        System.out.println(Integer.toBinaryString((int)Math.pow(2,30))+"  "+Integer.toBinaryString((int)Math.pow(2,30)).length());
    }

    public int reverseBits(int n) {
        int num=0;
        for (int i=0;i<32;i++){
            if ((n&1)!=0){
                if (i==0){
                    num=Integer.MIN_VALUE;  //如果要改变0的最高位（即符号位），直接让num为最小值即可(Integer.MIN_VALUE就是只有
                    // 最高位为1，其他都是0)  因为2的31次方并不是最高位为0，而是除符号位都是0 2的其他次方才是只有一个1
                }else{
                    num+=Math.pow(2,32-i-1);   //快捷记忆：len-1-i为从后面数起的第i个元素  如len=4时 i=0 倒数：4-0-1=3(倒数第0个的下标)
                }
            }
            n=n>>>1;
        }
        return num;
    }
}

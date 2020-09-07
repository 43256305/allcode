package leetcode.tencentfifty;

/**
 * @program: offer
 * @description: 回文数   判断数字是否为回文数（包括负号符）
 * @author: xjh
 * @create: 2020-06-04 08:40
 **/
public class IsPalindrome {

    public static void main(String[] args) {
        isPalindrome2(99999);
    }

    public boolean isPalindrome(int x) {
        if (x<0){
            return false;
        }
        char[] s=String.valueOf(x).toCharArray();
        int n=s.length;
        //下面这个循环可以判断字符串是否为回文数（奇数时：3  3/2=1  所以只判断了0   偶数时：2  2/2=1  所以只判断了0）
        for (int i=0;i<n/2;i++){
            if (s[i]!=s[n-1-i]){   //n-(1+i)求倒数第i+1个的下标，如i=0，则n-1为倒数第一个（与链表中倒数第i个一样原理）
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome1(int x) {
        if (x<0){
            return false;
        }
        char[] s=String.valueOf(x).toCharArray();
        int j=s.length-1;
        int i=0;
        //用while循环判断  （不能等于，如果是奇数则中间一位不用判断，如果是偶数则i一定不会等于j）  与上面其实差不多，但是比上面快一点
        while (i<j){
            if (s[i]!=s[j]){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static boolean isPalindrome2(int x) {
        //边界判断
        if (x < 0) return false;
        int div = 1;
        //
        while (x / div >= 10) div *= 10;    //相当于判断x的位数 如10000  div=10000   99999 div=10000
//        System.out.println(div);
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            x = (x % div) / 10;   //去掉x的首尾  如52345%10000=2345  2345/10=234
            //除以10是去掉尾巴，%10是取尾巴   除以它的位数是取首位，%它的位数是去除首位
            div /= 100;
        }
        return true;
    }

    public boolean isPalindrome3(int x) {
        //思考：这里大家可以思考一下，为什么末尾为 0 就可以直接返回 false（因为首位不可能为0）
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int revertedNumber = 0;
        //如x=52345最后r=543（就是x的后面一半反过来），x=52（就是x的前面一半）
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;   //不断取尾巴
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;   //x长度为偶数，则比较==，x长度为奇数，则r要/10（即去掉中间的元素）
    }

}

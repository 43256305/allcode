package leetcode.tencentfifty;

/**
 * @program: offer
 * @description:字符串转化整数
 * @author: xjh
 * @create: 2020-06-05 15:49
 **/
public class MyAtoi {

    public static void main(String[] args) {
        System.out.println(myAtoi(" -42"));
        System.out.println(Integer.MIN_VALUE*-1);   //还是为：-2147483648
        System.out.println(Integer.MIN_VALUE/-1);  //也没变
    }

    public static int myAtoi(String str) {
        char[] c=str.toCharArray();
        int num=0;
        int flag=1;
        boolean start=false;
        for (int i=0;i<c.length;i++){
            if (start){
                if (c[i]<'0'||c[i]>'9'){  //开始之后还出现非数字字符直接退出
                    break;
                }
                //判断溢出
                if (flag==1&&(num>Integer.MAX_VALUE/10||(num==Integer.MAX_VALUE/10&&(c[i]-'0')>7))){
                    return Integer.MAX_VALUE;
                }
                //下面的MIN_VALUE不能*-1变为正的，因为10000...*100..001还是100000...
                if (flag==-1&&(num>Integer.MIN_VALUE/-10||(num==Integer.MIN_VALUE/-10&&(c[i]-'0')>8))){
                    return Integer.MIN_VALUE;
                }
                num=num*10 + (c[i]-'0');  //char类型转化为int不能直接转化，直接转化是阿斯克码
            }else{

                if (' '==c[i]) continue;

                if ('+' == c[i]){
                    start=true;
                    continue;
                }
                if ('-' == c[i]){
                    flag=-1;
                    start=true;
                    continue;
                }

                if (c[i] >= '0'&& c[i] <= '9'){
                    num=c[i]-'0';
                    start=true;
                    continue;
                }
                break;
            }
        }
        return num*flag;
    }
}

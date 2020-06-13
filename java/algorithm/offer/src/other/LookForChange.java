package other;

import java.util.Scanner;

/**
 * @program: offer
 * @description: 找零问题
 * @author: xjh
 * @create: 2020-04-28 15:20
 **/
public class LookForChange {
    public static void main(String[] args) {
        change();
    }

    public static void change(){
        Scanner in=new Scanner(System.in);
        System.out.println("请输入金额（整型）：");
        int total=in.nextInt();
        System.out.println("请输入银币种类：");
        int m=in.nextInt();
        int[] coin=new int[m];
        int[] num=new int[m];
        System.out.println("请按从大到小输入硬币面额：");
        for (int i=0;i<coin.length;i++){
            coin[i]=in.nextInt();
        }

        System.out.println("找零结果以硬币‘面额：数量’的形式输出：");
        for (int i=0;i<coin.length;i++){
            num[i]=total/coin[i];
            if (num[i]!=0){
                System.out.print(coin[i]+":"+num[i]+"  ");
            }
            total=total%coin[i];
            if (total==0){
                break;
            }
        }

    }
}

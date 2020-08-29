import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: offer
 * @description: 输入输出测试
 * @author: xjh
 * @create: 2020-08-26 22:03
 **/
public class InputAndOutput {
    public static void main(String[] args) {
        //输入：
        // ksjdkjf  kjkjdf kjkj
        //输出：
        //ksjdkjf
        //  kjkjdf kjkj
//        Scanner in = new Scanner(System.in);
//        System.out.println(in.next());  //字符前的空格忽略，字符后遇到空格/换行停止
//        System.out.println(in.nextLine()); //从next()停止的位置开始读取后面的所有字符包括空格，遇到换行停止
        //所以输入一个字符后直接回车，nextLine()只能读取到回车符就立刻停止，事实上没有读取到字符

        //输入：
        // 5
        //aa nn kj iu lk
        //输出
        //aa iu kj lk nn
//        Scanner in = new Scanner(System.in);
//        int size = Integer.valueOf(in.nextLine());  //数据有两行时，需要用nextLine()而不能用nextInt()，要不然只能读取到数字5与回车符
//        String[] strings = in.nextLine().trim().split(" ");
//        Arrays.sort(strings);
//        for ( int i = 0;i<strings.length;i++){
//            if (i!=strings.length-1){
//                System.out.print(strings[i]+" "); //用Arrays输出会以[s,s,s]的形式，而题目要求s s s的形式
//            }else {
//                System.out.print(strings[i]);
//            }
//        }

        //输入：
        //kjkj
        //kkk
        //输出：
        //kjkj
        //kkk
//        Scanner in = new Scanner(System.in);
//        String a = in.next();
//        String b = in.next();  //从第一行的空格符开始读取，直到第二行的空格符停止
//        System.out.println(a);
//        System.out.println(b);


        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()){
            int size = in.nextInt();
            int sum = 0;
            for (int i = 0 ;i < size;i++){
                sum+=in.nextInt();
            }
            System.out.println(sum);
        }
    }
}

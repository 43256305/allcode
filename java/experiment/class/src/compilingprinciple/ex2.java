package compilingprinciple;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-06-04 10:30
 **/
public class ex2 {
    public static void main(String[] args) {
        int[][] DFA={{0,0,0,0},{3,2,0,0},{4,2,0,0},{0,6,3,5},{0,7,3,5},
                {4,0,0,0},{0,6,0,0},{0,6,0,0}};
        HashMap<Character,Integer> map=new HashMap();
        map.put('a',0);
        map.put('b',1);
        map.put('c',2);
        map.put('d',3);
        int k=1;
        Scanner in=new Scanner(System.in);
        System.out.println("请在一行内输入，#结束：");
        String s=in.nextLine();
        char[] e=s.toCharArray();
        int i=0;
        while ('#'!=e[i]){
            if (!map.containsKey(e[i])){
                System.out.println("输入不合法");
                System.exit(1);
            }
            k=DFA[k][map.get(e[i])];
            i++;
        }
        if (k==6||k==7){
            System.out.println("yes");
        }else {
            System.out.println("no");
        }
    }
}

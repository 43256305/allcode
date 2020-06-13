package compilingprinciple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-06-11 10:02
 **/
public class ex3 {
    private static Map<Character,Integer> mapVn;
    private static Map<Character,Integer> mapVt;
    private static Stack<Character> stack=new Stack();
    private static String[][] table={{"TD","","","TD","",""},{"","+TD","","","e","e"},{"FS","","","FS","",""},{"","e","*FS","","e","e"},{"i","","","(E)","",""}};;

    public static void main(String[] args) {
        prepare();
        analysis();
    }

    public static void prepare(){
        mapVn=new HashMap<Character, Integer>(){
            {
                put('E',0);
                put('D',1);   //D代表E‘   e代表空符
                put('T',2);
                put('S',3);   //S代表T’
                put('F',4);
            }
        };
        mapVt=new HashMap<Character, Integer>(){
            {
                put('i',0);
                put('+',1);
                put('*',2);
                put('(',3);
                put(')',4);
                put('#',5);
            }
        };
        stack.push('#');
        stack.push('E');
    }

    public static void analysis(){
        String input="i+i*i#";
        char[] inputChar=input.toCharArray();
        int i=0;
        while (true){
            char pop=stack.pop();
            if ('#'==pop){
                System.out.println("接受");
                System.exit(0);
            }
            int x=0;
            if (mapVn.containsKey(pop)){
                x=mapVn.get(pop);
            }else{
                if (pop==inputChar[i]){
                    stack.push(pop);
                    String result=("分析栈："+stack+"   剩余输入串："+new String(inputChar).substring(i)+"   产生式：匹配"+pop).replaceAll("D","E'").replaceAll("S","T'");
                    System.out.println(result);
                    i++;
                    stack.pop();
                    continue;
                } else{
                    System.out.println("不匹配,栈顶与输入不匹配");
                    System.exit(0);
                }
            }
            int y=mapVt.get(inputChar[i]);
            char[] pushStack=table[x][y].toCharArray();
            if (pushStack.length==0){  //匹配到""，报错
                System.out.println("不匹配,table此位置没有值");
                System.exit(0);
            }
            if (pushStack.length==1&&pushStack[0]=='e'){
                String result=("分析栈："+stack+"   剩余输入串："+new String(inputChar).substring(i)+"   产生式："+"e").replaceAll("D","E'").replaceAll("S","T'");
                System.out.println(result);
                continue;
            }
            for (int j=pushStack.length-1;j>=0;j--){
                stack.push(pushStack[j]);
            }
            String result=("分析栈："+stack+"   剩余输入串："+new String(inputChar).substring(i)+"   产生式："+table[x][y]).replaceAll("D","E'").replaceAll("S","T'");
            System.out.println(result);
        }
    }
}

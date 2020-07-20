package compilingprinciple;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-06-23 17:01
 **/
public class ex5 {
    private static String action[][] = {
            {"S2","S3","","",""},
            {"","","","","acc"},
            {"","","S4","S10",""},
            {"","","S5","S11",""},
            {"","","S4","S10",""},
            {"","","S5","S11",""},
            {"r1","r1","r1","r1","r1"},
            {"r2","r2","r2","r2","r2"},
            {"r3","r3","r3","r3","r3"},
            {"r5","r5","r5","r5","r5"},
            {"r4","r4","r4","r4","r4"},
            {"r6","r6","r6","r6","r6"}
    };
    private static int[][] GOTO = {
            {1,0,0}, {0,0,0}, {0,6,0}, {0,0,7},
            {0,8,0}, {0,0,9}, {0,0,0}, {0,0,0},
            {0,0,0}, {0,0,0}, {0,0,0}, {0,0,0}
    };
    private static Deque<Integer> state = new ArrayDeque<>();
    private static Deque<Character> symbol = new ArrayDeque<>();
    private static String[] G = {"S':E","E:aA","E:bB","A:eA","A:d","B:cB","B:d"};
    private static Map<Character,Integer> map = new HashMap<Character, Integer>(){
        {
            put('a',0);
            put('b',1);
            put('c',2);
            put('d',3);
            put('#',4);
            put('E',0);
            put('A',1);
            put('B',2);
        }
    };

    public static void main(String[] args) {
        analysis();
    }

    public static void analysis(){
        state.push(0);
        symbol.push('#');
        String input = "bccd#";
        int i = 0;
        while (true){
            String str = action[state.peek()][map.get(input.charAt(i))];
            if (str.charAt(0) == 'S'){
                symbol.push(input.charAt(i));
                int num = Integer.valueOf(str.substring(1,str.length()));
                state.push(num);
                System.out.println("状态栈："+state+" 符号栈："+symbol+" 输入串："+input.substring(i,input.length())+" ACTION:"+str);
                i++;
            }else if (str.charAt(0) == 'r'){
                int num = Integer.valueOf(str.substring(1,str.length()));
                String rG = G[num];
                String left = rG.split(":")[0];
                String right = rG.split(":")[1];
                for (int j = 0 ;j<right.length();j++){
                    state.pop();
                    symbol.pop();
                }
                symbol.push(left.charAt(0));
                int gotoState = GOTO[state.peek()][map.get(left.charAt(0))];
                state.push(gotoState);
                System.out.println("状态栈："+state+" 符号栈："+symbol+" 输入串："+input.substring(i,input.length())+" ACTION:"+str);
            }else {   //acc
                System.out.println("结束");
                break;
            }
        }
    }
}

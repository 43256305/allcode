package compilingprinciple;


import java.util.*;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-06-18 09:40
 **/
public class ex4 {
    private static Map<Character,Integer> priorityMap;
    private static Deque<Character> stack = new ArrayDeque<>();
    private static char[][] priorityTable = {
            {'>','<','<','<','<','>','>'},{'>','>','<','<','<','>','>'},{'>','>','<','<','<','>','>'},{'>','>','>','0','0','>','>'},
            {'<','<','<','<','<','=','0'},{'>','>','>','0','0','>','>'},{'<','<','<','<','<','0','='}
    };

    public static void main(String[] args) {
        prepare();
        solution();
    }

    private static void prepare(){
        priorityMap = new HashMap<Character, Integer>(){
            {
                put('+',0);
                put('*',1);
                put('|',2);
                put('i',3);
                put('(',4);
                put(')',5);
                put('#',6);
            }
        };
    }

    private static void solution(){
        stack.push('#');
        String input = "i+i#";
        System.out.println("下面的流程并不输出非终结符N！");

        for (int i = 0;i < input.length();i ++){
            if (stack.peek() == 'N'){  //在算符优先中只有一个非终结符，就是N
                stack.pop();
            }
            if (priorityTable[priorityMap.get(stack.peek())][priorityMap.get(input.charAt(i))] == '>'){
                OUT:while (true){
                    char Q = stack.pop();
                    if (stack.peek() == 'N'){
                        stack.pop();
                    }
                    if (priorityTable[priorityMap.get(stack.peek())][priorityMap.get(Q)] == '<'){
                        System.out.println("栈："+stack+" 优先关系："+">"+" 当前输入符:"+input.charAt(i)+" 剩余输入符："+input.substring(i+1,input.length())+"  规约");
                        stack.push('N');
                        i--;
                        break OUT;
                    }
                }
            }else if (priorityTable[priorityMap.get(stack.peek())][priorityMap.get(input.charAt(i))] == '<'){
                System.out.println("栈："+stack+" 优先关系："+"<"+" 当前输入符:"+input.charAt(i)+" 剩余输入符："+input.substring(i+1,input.length())+"  移进");
                stack.push(input.charAt(i));
            }else {
                if (stack.peek() == input.charAt(i)){
                    if (stack.peek() == '#'){
                        System.out.println("结束");
                        System.exit(1);
                    }else {
                        System.out.println("栈："+stack+" 优先关系："+"<"+" 当前输入符:"+input.charAt(i)+" 剩余输入符："+input.substring(i+1,input.length())+"  移进");
                        stack.push(input.charAt(i));
                    }
                } else {
                    System.out.println("出错！");
                    System.exit(1);
                }
            }
        }
    }

}

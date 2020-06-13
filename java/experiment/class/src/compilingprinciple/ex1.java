package compilingprinciple;

import java.util.*;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-05-28 10:16
 **/
public class ex1 {
    private static List<String> G=new ArrayList<>();
    private static Set<Character> VN=new HashSet<>();
    private static Set<Character> VT=new HashSet<>();
    public static void main(String[] args) {
        input();
        find();
        type();
    }

    public static void input(){
        Scanner in=new Scanner(System.in);
        System.out.println("选择1用默认文法，选择2手动输入文法：");
        String select=in.nextLine();
        if ("1".equals(select)){
            String[] G1={"aS:aB","bS:bA","A:bAA","A:a","A:aS","B:b","B:bS","B:aBB"};
            G= Arrays.asList(G1);
        }else if ("2".equals(select)){
            System.out.println("输入一行文法后请回车，最后一行以#键结束输入");
            String s=in.nextLine();
            while (!"#".equals(s)){
                G.add(s);
                s=in.nextLine();
            }
        }

    }

    public static void find(){
        for (int i=0;i<G.size();i++){
            if (!G.get(i).contains(":")||G.get(i).split(":").length!=2){
                System.out.println("输入文法存在错误！");
                System.exit(1);
            }
            char[] chars=G.get(i).toCharArray();
            for (int j=0;j<chars.length;j++){
                if (chars[j]>='A'&&chars[j]<='Z'){
                    VN.add(chars[j]);
                }else if(chars[j]!=':') {
                    VT.add(chars[j]);
                }
            }
        }
        System.out.println("非终结符："+VN);
        System.out.println("终结符："+VT);
    }

    public static void type(){
        int three=1;
        int one=1;
        for (int i=0;i<G.size();i++){

            char[] left=G.get(i).split(":")[0].toCharArray();
            char[] right=G.get(i).split(":")[1].toCharArray();
            if (right==null||right.length==0||left==null||left.length==0){   //长度为0，不是文法
                System.out.println("不是文法");
                return;
            }

            int flag=0;
            for (int k=0;k<left.length;k++){   //左边最少包含一个非终结符
                if (VN.contains(left[k])){
                    flag=1;
                }
            }
            if (flag==0){
                System.out.println("不是文法");
                return;
            }
            flag=0;

            if (three==1&&left.length==1&&VN.contains(left[0])){
                if (right.length==2){
                    if (VT.contains(right[0])&&VN.contains(right[1])&&i==G.size()-1){
                        System.out.println("3型文法");   //三型文法（正规文法）：左边只有一个非终结符，右边形如aB或者a
                        return;
                    }
                }else if (right.length==1){
                    if (VT.contains(right[0])&&i==G.size()-1){
                        System.out.println("3型文法");
                        return;
                    }
                }
                if (i==G.size()-1){
                    System.out.println("2型文法");  //二型文法(上下文无关文法)：左边只有一个非终结符，右边随意
                    return;
                }
            }else{
                three=0;
                if (one==1&&left.length<=right.length){  //1型文法：左边长度小于等于右边
                    if (i==G.size()-1){
                        System.out.println("1型文法");
                        return;
                    }
                }else{
                    one=0;
                    System.out.println("0型文法");   //0型文法：左边一定有一个非终结符
                    return;
                }
            }

        }

    }
}

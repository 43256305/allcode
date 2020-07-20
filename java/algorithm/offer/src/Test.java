import java.util.*;

/**
 * @program: offer
 * @description:
 * @author: xjh
 * @create: 2020-06-15 08:41
 **/
public class Test {
    private static Test test = new Test();
    public static void main(String[] args) {
//        System.out.println(solution());
//        System.out.println(Integer.MAX_VALUE+1);
    }

    public static int solution(){
        Scanner in = new Scanner(System.in);
        int num = Integer.valueOf(in.nextLine());
        int[] prices = new int[num];
        String[] temp = in.nextLine().split(" ");
        for (int i = 0;i < num;i ++){
            prices[i] = Integer.valueOf(temp[i]);
        }
        int money = Integer.valueOf(in.nextLine());

        return backTrack(-1,money,prices);
    }

    public static int backTrack(int i,int money,int[] prices){
        if (money == 0){
            return 1;
        }

        if (money < 0){
            return 0;
        }


        i++;
        for (;i<prices.length;i++){
            if (backTrack(i,money-prices[i],prices)==1){
                return 1;
            }
        }

        return 0;
    }

}

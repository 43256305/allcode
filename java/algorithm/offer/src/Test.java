import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: offer
 * @description:
 * @author: xjh
 * @create: 2020-06-15 08:41
 **/
public class Test {
    private static Test test = new Test();
    public static void main(String[] args) {
        List<List<Integer>> res =  permute(3);
        System.out.println(res);
    }

    public static List<List<Integer>> permute(int n){  //1-n的全排列
        List<Integer> track = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        backTrack(track,n,res);

        return res;

    }

    public static void backTrack(List track,int n,List<List<Integer>> res){
        if (track.size() == n){
            res.add(new ArrayList<>(track));
            return ;
        }

        for (int i = 1;i <= n;i++){
            if (track.contains(i)){
                continue;
            }
            track.add(i);
            backTrack(track,n,res);
            track.remove((Integer)i);
        }
    }
}

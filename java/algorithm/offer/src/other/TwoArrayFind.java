package other;

/**
 * @program: offer
 * @description: 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * @author: xjh
 * @create: 2020-03-07 20:31
 **/
public class TwoArrayFind {
    
    /**
    * @Description: 开始：从第0行的最后一列开始，大于他，就可以排除第一行，则x++，小于他，则可以排除一列，y--   时间复杂度：O(行高+列高)
     * 两个变量x,y代表坐标
    * @Param: 
    * @return: boolean
    * @Author: xjh
    * @Date: 2020/3/7
    */
    public boolean Find(int target, int [][] array) {
        int x=0;
        int y=array[0].length-1;
        while(x<array.length&&y>=0){
//            if (x>=array.length||y<0){  //很显然，出来的条件在循环的开头，可以放在循环条件里，但是条件要相反。原来循环里时true
//                return false;
//            }
            if (target<array[x][y]){
                y--;
            }else if (target>array[x][y]){
                x++;
            }else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TwoArrayFind a=new TwoArrayFind();
        int[][] b={{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        System.out.println(a.Find(7,b));
    }
}

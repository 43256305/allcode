package other;

/**
 * @program: offer
 * @description: 用动态规划找出最优二叉查找树
 * @author: xjh
 * @create: 2020-04-06 18:15
 **/
public class OptimalBinarySearchTree {
    public static void main(String[] args) {

    }

    /**
    * @Description:
    * @Param: p:二叉树节点的概率数组 从1开始
    * @return:
    * @Author: xjh
    * @Date: 2020/4/6
    */
    public static void optimal(double[] p){
        int n=p.length;
        int[][] R=new int[n+2][n+1];  //根表:记录最小的k值
        double[][] C=new double[n+2][n+1];  //主表：记录最小概率  i为0的行不用
        for (int i=1;i<=n;i++){
            C[i][i-1]=0;
            C[i][i]=p[i];
            R[i][i]=i;
        }
        C[n+1][n]=0;
        int j;
        int minK=Integer.MIN_VALUE;
        double minval;
        for (int d=1;d<n-1;d++){
            for (int i=1;i<n-d;i++){
                j=i+d;
                minval=Double.MAX_VALUE;
                for (int k=i;k<j;k++){
                    if (C[i][k-1]+C[k+1][j]<minval){
                        minval=C[i][k-1]+C[k+1][j];
                        minK=k;
                    }
                    R[i][j]=minK;
                }
            }
        }
    }
}

package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @program: offer
 * @description: 最大矩形
 * @author: xjh
 * @create: 2020-06-12 17:13
 **/
public class MaximalRectangle {
    private static MaximalRectangle maximalRectangle=new MaximalRectangle();

    public static void main(String[] args) {
        char[][] array={
                {'1','0','1','0','0'},
                {'1','0','1','1','1'}
        };
        System.out.println(maximalRectangle.maximalRectangle1(array));
    }


    /**
    * @Description: 递增栈  时间复杂度mn
     * 把矩阵改为柱状图，这样就有matrix.length个柱状图，计算每个柱状图的最大面积，然后再求出矩阵最大面积
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/12
    */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length==0){
            return 0;
        }
        int rowLen=matrix.length;
        int colLen=matrix[0].length;
        int[][] matrixNum=new int[rowLen][colLen];
        for (int i=0;i<colLen;i++){
            matrixNum[0][i]=matrix[0][i]-'0';
        }
        //构造高度矩阵（柱状图）
        for (int i=1;i<rowLen;i++){
            for (int j=0;j<colLen;j++){
                if (matrix[i][j]=='1'){
                    matrixNum[i][j]=matrixNum[i-1][j]+1;  //事实上这里计算高度的matrixNum矩阵可以简化为一个数组，因为每一行都只依赖于他上一行
                }else {
                    matrixNum[i][j]=0;
                }
            }
        }
        int ans=0;
        for (int i=0;i<rowLen;i++){
            ans=Math.max(ans,largestRectangleArea2(matrixNum[i]));
        }
        return ans;
    }

    public int largestRectangleArea2(int[] heights){
        int len=heights.length;
        int[] left=new int[len];
        int[] right=new int[len];
        Arrays.fill(right,len);

        Stack<Integer> stack=new Stack<>();
        for (int i=0;i<len;i++){
            while (!stack.isEmpty()&&heights[stack.peek()]>=heights[i]){
                right[stack.peek()]=i;
                stack.pop();
            }
            left[i] = stack.isEmpty()?-1:stack.peek();
            stack.push(i);
        }

        int ans=0;
        for (int i=0;i<len;i++){
            ans=Math.max(ans,heights[i]*(right[i]-left[i]-1));
        }

        return ans;
    }


    /**
    * @Description: 动态规划  时间复杂度 mn
     * 把左侧第一个小于i和右侧第一个小于i用栈生成改为用循环生成（根据上一行）
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/12
    */
    public int maximalRectangle1(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int maxArea = 0;
        int cols = matrix[0].length;
        int[] leftLessMin = new int[cols];   //第i行左侧第一个小于i的下标
        int[] rightLessMin = new int[cols];  //第i行左侧第一个小于i的下标
        Arrays.fill(leftLessMin, -1);  //初始化为 -1，也就是最左边
        Arrays.fill(rightLessMin, cols);   //初始化为 cols，也就是最右边
        int[] heights = new int[cols];   //第i行的高度
        for (int row = 0; row < matrix.length; row++) {
            //更新所有高度
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == '1') {
                    heights[col] += 1;
                } else {
                    heights[col] = 0;
                }
            }
            //更新所有leftLessMin
            int boundary = -1; //记录上次出现 0 的位置
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == '1') {
                    //和上次出现 0 的位置比较
                    leftLessMin[col] = Math.max(leftLessMin[col], boundary);
                } else { //当前是 0 代表当前高度是 0，所以初始化为 -1，防止对下次循环的影响
                    leftLessMin[col] = -1;
                    //更新 0 的位置
                    boundary = col;
                }
            }
            //右边同理
            boundary = cols;
            for (int col = cols - 1; col >= 0; col--) {
                if (matrix[row][col] == '1') {
                    rightLessMin[col] = Math.min(rightLessMin[col], boundary);  //为什么上面是最大，而这里是最小呢？
                    //因为右边最近最小，最近（right）就是最小，而上面（left）最近就是最大
                } else {
                    rightLessMin[col] = cols;
                    boundary = col;
                }
            }

            //更新所有面积
            for (int col = cols - 1; col >= 0; col--) {
                int area = (rightLessMin[col] - leftLessMin[col] - 1) * heights[col];
                maxArea = Math.max(area, maxArea);
            }

        }
        return maxArea;

    }
}

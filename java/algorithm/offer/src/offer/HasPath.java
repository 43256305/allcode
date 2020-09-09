package offer;

/**
 * @program: 剑指offer
 * https://www.nowcoder.com/practice/c61c6999eecb4b8f88a98f66b273a3cc?tpId=13&&tqId=11218&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子
 * @description:
 * @author: xjh
 * @create: 2020-09-09 08:11
 **/
public class HasPath {

    class Solution {
        /**
        * @Description:
         * matrix是用数组代表矩阵，把row行的数据放在一行，所以原来i，j位置的元素现在i*row+j位置
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/9/9
        */
        public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
            int flag[] = new int[matrix.length];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (helper(matrix, rows, cols, i, j, str, 0, flag))   //只要有一个满足就退出
                        return true;
                }
            }
            return false;
        }

        /**
        * @Description:
         * k代表str的下标   flag[index]代表此位置是否曾经匹配过   index代表矩阵中的位置
        * @Param:
        * @return:
        * @Author: xjh
        * @Date: 2020/9/9
        */
        private boolean helper(char[] matrix, int rows, int cols, int i, int j, char[] str, int k, int[] flag) {
            int index = i * cols + j;
            //剪枝（j/i超出了范围，此位置与str不想等，flag中此位置已经被匹配过）
            if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] || flag[index] == 1)
                return false;
            //匹配成功
            if(k == str.length - 1) return true;
            flag[index] = 1;
            //匹配下一个，向此位置的前后左右找，因为flag已经有标记了，所以不用判断方向，判断过的地方自然会退出
            if (helper(matrix, rows, cols, i + 1, j, str, k + 1, flag)
                    || helper(matrix, rows, cols, i - 1, j, str, k + 1, flag)
                    || helper(matrix, rows, cols, i, j + 1, str, k + 1, flag)
                    || helper(matrix, rows, cols, i, j - 1, str, k + 1, flag))
                return true;

            flag[index] = 0;  //下一个没有匹配到，则此位置没有路，把flag置为0（因为hasPath方法也有循环需要匹配下一个，所以要确保没有匹配成功时flag回退）
            return false;
        }
    }
}

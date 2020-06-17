package leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: offer
 * @description: 八皇后
 * @author: xjh
 * @create: 2020-06-16 19:07
 **/
public class SolveNQueens {
    private static SolveNQueens queens = new SolveNQueens();

    public static void main(String[] args) {
        queens.solveNQueens(4);
    }


    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] nums = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(nums[i], '.');
        }
        backtrack(nums,0, ans);  //当前行从0开始寻找
        return ans;
    }

    /**
     * @Description: 回溯法  当探索到某一步时，发现原先选择并不优或达不到目标，就退回一步重新选择，这种走不通就退回再走的技术为回溯法
     *
     * 回溯法：不满足条件（剪枝）直接返回，满足最终条件加入结果集，循环中满足递归条件则调用递归
     * 适用：排列、组合（子集、幂集、字符全排列）。 在传值时，对于排列问题，是要删掉单个用过的元素；组合问题，是删掉前面所有的元素。
     * 数组、字符串，给定一个特定的规则，尝试搜索迭代找到某个解。  二维数组下的DFS搜索（八皇后、黄金矿工、数独）
     *
     * 八皇后问题：路径：包含Q的二维数组与curRow  选择列表：curRow那些列可以设为Q  结束条件：curRow到达底层
     * @Param:
     * @return:
     * @Author: xjh
     * @Date: 2020/6/16
     */
    private void backtrack(char[][] nums, int currRow, List<List<String>> ans) {
        int len = nums.length;
        if (len == currRow) {  //出口：如果curRow是最后一行加1，则说明已经找完了
            List<String> path2 = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                path2.add(String.valueOf(nums[i]));
            }
            ans.add(path2);
            return;
        }

        //从00开始位置自上而下遍历，直到当前行(不包括)，遍历完一列在遍历一列 即00 10 20 ... (curRow-1)0 01 11 21 ... (curRow-1)1...
        // 遍历到没有冲突的列col后，在currow行与找出的col列位置，填入Q  如当curRow=0时，显然，第一列就没有冲突，所以可以填入。填入Q后
        //递归，curRow+1，即在下一行找出正确的位置，然后消去当前位置的Q，从下一行开始又重新找不冲突的列，如当curRow=0时，第二列没有冲突
        //注意最后curRow是遍历不到的，我们只是为了选取一个合适的col，而行早已注定是curRow
        for (int col = 0; col < len; col++) {
            //判断这个位置是否合适
            boolean isok = true;
            for (int row = 0; row < currRow; row++) {
                //竖的有Q
                if (nums[row][col] == 'Q') {  //竖的位置有Q直接退出内存循环，不用遍历当前的row了
                    isok = false;
                    break;
                }
                //判断右对角线（即我们最后选出col时，要确保它的右对角线没有Q，如果curRow=3，那么要确保col=0时，在当前遍历的第row行，它的
                // 对角线不为Q，如果row也为0，不为Q的点就是[0][0+3-0],如果row=1，不为Q的点是[1][0+3-1=2]）
                if (col + (currRow - row) < len && nums[row][col + (currRow - row)] == 'Q') {
                    isok = false;
                    break;
                }
                //判断左对角线  如row=0，col=1，curRow=1时，nums[0][1-1]==Q，所以当前col不满足，为什么当前遍历点为 [0][1]，但是却
                //判断[0][0]位置呢？因为我们最后要的是col，而当前col=1，即如果满足，最后[curRow][col]赋值为Q，那么判断它的对角线即判断
                //他的上一行的左边是否有Q，在这个例子中，有Q，所以col=1不能取
                if (col - (currRow - row) >= 0 && nums[row][col - (currRow - row)] == 'Q') {
                    isok = false;
                    break;
                }
            }
            if (!isok) {  //如果此位置不满足，继续 col++
                continue;
            }
            //满足条件
            nums[currRow][col] = 'Q';
            backtrack(nums, currRow + 1, ans);
            nums[currRow][col] = '.';   //重新赋值为.，重新从下一列开始找
        }
    }
}

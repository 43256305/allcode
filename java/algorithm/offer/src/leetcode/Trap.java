package leetcode;

/**
 * @program: offer
 * @description: 接雨水   https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
 * @author: xjh
 * @create: 2020-06-05 09:56
 **/
public class Trap {
    
    /**
    * @Description: 暴力法，按行求   时间：m*n  空间：1
     * 如果求高度为 i 的水，首先用一个变量 temp 保存当前累积的水，初始化为 0。从左到右遍历墙的高度，遇到高度大于等于 i 的时候，开始更新
     * temp。更新原则是遇到高度小于 i 的就把 temp 加 11，遇到高度大于等于 i 的，就把 temp 加到最终的答案 ans 里，并且 temp 置零，然后继续
     * 循环。
     *变量：i(1,i<=max) j(0,j<n.length)
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/5
    */
    public int trap(int[] height) {
        int sum = 0;
        int max = getMax(height);//找到最大的高度，以便遍历。
        for (int i = 1; i <= max; i++) {
            boolean isStart = false; //标记是否开始更新 temp   遇到高度大于等于i则开始更新
            int temp_sum = 0;
            for (int j = 0; j < height.length; j++) {
                if (isStart && height[j] < i) {
                    temp_sum++;
                }
                if (height[j] >= i) {   //遇到高度大于等于i则开始收集（isStart=true）或者停止收集（sum+=temp，temp=0），收集i高度中间小于i的水量
                    sum = sum + temp_sum;
                    temp_sum = 0;
                    isStart = true;
                }
            }
        }
        return sum;
    }
    private int getMax(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
            }
        }
        return max;
    }

    /**
    * @Description: 按列求  时间：n^2  空间：1
     * 求出左边最高的强和右边最高的强并比较出一个较矮的墙，只有当前高度小于较矮的墙才能积水
     * 变量：i（1,i<n-1）  j（i-1,0   i+1,i<n）
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/5
    */
    public int trap1(int[] height) {
        int sum = 0;
        //最两端的列不用考虑，因为一定不会有水。所以下标从 1 到 length - 2
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0;
            //找出左边最高
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }
            int max_right = 0;
            //找出右边最高
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }
            //找出两端较小的
            int min = Math.min(max_left, max_right);
            //只有较小的一段大于当前列的高度才会有水，其他情况不会有水
            if (min > height[i]) {
                sum = sum + (min - height[i]);   //用较小的值减去当前，就是雨水
            }
        }
        return sum;
    }

    /**
    * @Description: 动态规划（记忆了左右两边墙最高的高度，所以不用每次都计算）   时间：n  空间：n
     * 相比按列求增加了两个数组max_left [i] 代表第 i 列左边最高的墙的高度（不包括i），max_right[i] 代表第 i 列右边最高的墙的高度。（不包括j）
     * 动态规划推导式：max_left [i] = Max(max_left [i-1],height[i-1])  max_right[i] = Max(max_right[i+1],height[i+1])
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/5
    */
    public int trap2(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            //因为i时最高的墙不包括i，所以i=1时，最高的就是height[0]
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);  //因为有i-1，所以i从1开始直到最后
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {  //0和n-1列都不可能存水
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    /**
    * @Description: 双指针（动态规划优化空间复杂度）  时间：n  空间：1
     * 假设一开始left-1大于right+1，则之后right会一直向左移动，直到right+1大于left-1。在这段时间内right所遍历的所有点都是左侧最高点
     * maxleft大于右侧最高点maxright的，所以只需要根据原则判断maxright与当前高度的关系就行。反之left右移，所经过的点只要判断maxleft与
     * 当前高度的关系就行。
     * 变量：left（1）,right（n-2）谁小谁移动，每次都选小的与当前height比较（高的一边虽然不是最高值，但也比另一边大）
     * 当前值取height[left]或者height[right]，当左边（即left-1）的矮时取left，右边（right-1）的矮时取right，因为他那边的矮就取那边
     * 所以中间的不用考虑，就算中间有更高的也不影响
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/5
    */
    public int trap3(int[] height) {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        int left = 1;
        int right = height.length - 2; // 加右指针进去
        for (int i = 1; i < height.length - 1; i++) {   //要进行n-2轮计算（去掉首位），i只是标记n-2轮，在循环中不起作用
            //从左到右更
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(max_left, height[left - 1]);  //当i++时，新的max变为了左边第一个与原来的max中的最大值
                int min = max_left;
                if (min > height[left]) {
                    sum = sum + (min - height[left]);
                }
                left++;
                //从右到左更
            } else {
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                if (min > height[right]) {
                    sum = sum + (min - height[right]);
                }
                right--;
            }
        }
        return sum;
    }

}

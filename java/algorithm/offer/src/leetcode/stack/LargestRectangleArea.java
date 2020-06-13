package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @program: offer
 * @description: 柱状图中最大矩形
 * @author: xjh
 * @create: 2020-06-12 14:22
 **/
public class LargestRectangleArea {
    private static LargestRectangleArea rectangleArea=new LargestRectangleArea();

    public static void main(String[] args) {
        int[] array={2,1,5,6,2,3};
        System.out.println(rectangleArea.largestRectangleArea2(array));
    }

    /**
    * @Description: 暴力法  n^2
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/12
    */
    public int largestRectangleArea(int[] heights) {
        int len=heights.length;
        int min=0;
        int area=0;
        for (int i=0;i<len;i++){
            area=Math.max(area,1*heights[i]);  //一根面积柱子也要计算
            min = heights[i];
            for (int j = i + 1;j < len;j ++){  //计算i-j  2根以上主子面积
                if (heights[j] < min) min = heights[j];
                area=Math.max(area,(j-i+1)*min);
            }
        }

        return area;
    }

    /**
    * @Description: 单调栈
     *选取某个柱子i，则包括i这个柱子的最大面积是多少呢？左侧第一个小于i和右侧第一个小于i的的距离（不包括左侧第一个小于i的和右侧第一
     * 个小于i的，也就是只取中间）乘以i的高度就是最大面积了，如2 3(i) 2 ，i=3，最大面积是（2-1）*3=9，如果左侧或者右侧没有小于i的
     * 怎么办呢？那么显然可以扩展到最左边（-1，因为不包括两边，所以为-1，也就是从0开始算）或者最右边（n）
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/12
    */
    public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];   //每一个元素左侧最近且小于其高度的柱子下标  如果左侧没有比他小的，则为-1
        int[] right = new int[n];  //为一个元素右侧最近且小于其高度的柱子下标，如果右侧没有比他小的，则为n

        Stack<Integer> mono_stack = new Stack<Integer>();
        for (int i = 0; i < n; ++i) {
            //当栈顶元素的高度大于i的高度时，出栈，i进栈，当栈顶元素高度小于i的高度时，i进栈（说明栈是递增的，且数组每一个元素都要入栈一次
            // 如果已经入栈的大于正在入栈的，则出栈，直到可以把此元素放入）
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());  //要么为-1，要么为当前栈顶元素，当前栈顶元素必定小于
            //当前正在进栈（即i）的值
            mono_stack.push(i);
        }

        mono_stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);  //right-left+1为包括最左和最右的距离，right-left-1为
            //不包括最左和最右的距离，（right+left）/2为中间位置  如0 1 2 3 4  4-2-1=1（2） 4-2+1=3（2 3 4） （4+2)/2=3
        }
        return ans;
    }

    
    /**
    * @Description: 单调栈优化  时间复杂度n
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/12
    */
    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);   //用n值填充right数组

        //填充left时也可以填充right
        Stack<Integer> mono_stack = new Stack<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i; //出栈时，i位置一定是正在出栈的元素右边最近比他小的，如5 6 2 3，i位置为2时，5 6出栈
                //5 6位置右边最近比他小的一定是正在入栈的2
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}

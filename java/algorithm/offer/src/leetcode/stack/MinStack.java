package leetcode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: offer
 * @description: 最小栈
 * @author: xjh
 * @create: 2020-06-12 23:09
 **/
public class MinStack {
    public static void main(String[] args) {
        MinStack stack=new MinStack();
        stack.push(Integer.MAX_VALUE);
        stack.push(Integer.MAX_VALUE);
        stack.push(Integer.MAX_VALUE);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.push(Integer.MAX_VALUE);
        stack.push(Integer.MIN_VALUE);
        System.out.println(stack.getMin());
    }

    private Stack<Integer> minStack;
    private List<Integer> list;
    private int last;

    public MinStack() {
        minStack = new Stack<>();
        list = new ArrayList<>();
        last = -1;
    }

    public void push(int x) {
        last++;
        list.add(x);
        if (minStack.isEmpty()||x<list.get(minStack.peek())){
            minStack.push(last);
        }
    }

    public void pop() {
        if (minStack.peek()==last){
            minStack.pop();
        }
        list.remove(last);
        last--;
    }

    public int top() {
        return list.get(last);
    }

    public int getMin() {   //返回栈中最小元素
        return list.get(minStack.peek());
    }
}

package other;

import java.util.Stack;

/**
 * @program: offer
 * @description: 用两个栈来实现一个队列
 * @author: xjh
 * @create: 2020-03-21 18:23
 **/
public class ImpQueue {
    private Stack<Integer> stack1 = new Stack<Integer>();
    private Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    /**
    * @Description: 把入栈全放置在stack1，然后出栈时转换到stack2，出栈一个元素后，又全部转换为stack1(栈反过来放就是队列（栈栈为队）)
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/21
    */
    public int pop() {
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        int temp=stack2.pop();
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return temp;
    }

    public static void main(String[] args) {
        ImpQueue queue=new ImpQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        System.out.println(queue.pop());
        queue.push(5);
        queue.push(6);
        for (int i=0;i<5;i++){
            System.out.println(queue.pop());
        }
    }
}

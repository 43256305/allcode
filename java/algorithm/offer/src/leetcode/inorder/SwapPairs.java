package leetcode.inorder;

import leetcode.ListNode;

/**
 * @program: offer
 * @description: 两两交换链表中的节点
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * @author: xjh
 * @create: 2020-06-19 21:16
 **/
public class SwapPairs {
    
    /**
    * @Description: 递归
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/19
    */
    public ListNode swapPairs(ListNode head) {
        ListNode temp = head;
        if (head != null && head.next != null){
            temp = head.next; //2
            head.next = head.next.next;  //1 3 4
            temp.next = head;  //2 1 3 4
            temp.next.next = swapPairs(temp.next.next);  //因为swap函数中，头节点改变了，而temp还指向原来的头节点，所以需要重新赋值为新的头节点
        }
        return temp;
    }
}

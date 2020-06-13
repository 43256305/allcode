package leetcode.doublepointer;


import leetcode.ListNode;

/**
 * @program: offer
 * @description:删除链表倒数第N个节点
 * @author: xjh
 * @create: 2020-06-08 15:11
 **/
public class RemoveNthFromEnd {
    
    /**
    * @Description: 双指针
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/8
    */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode q=null,p=head;
        int i=0;
        while (p.next!=null){   //最后p一定是指向最后一个
            p=p.next;
            i++;
            if (i==n){
                q=head;   //q指向倒数第n个的前一个
            }else if (i>n){
                q=q.next;
            }
        }
        if (i<n){  //删除头节点   i小于n时，一定是要删除头节点
            return head.next;
        }
        q.next=q.next.next;
        return head;
    }


}

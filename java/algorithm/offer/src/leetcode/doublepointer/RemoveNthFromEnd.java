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
            if (i == n){ //当i=n时，q才指向head，q与p之间相差n+1个节点（包括两边），所以i从0开始。如果q指向倒数第n个，那么就要相差n个节点，即i从1开始
                q=head;   //q指向倒数第n个的前一个  q就是指向从后数的第n+1个，所以从后数的下标为n，所以从0开始到n停止
            }else if (i>n){
                q=q.next;
            }
        }
        if (i<n){  //删除头节点   i小于n时，一定是要删除头节点  如链表为：8 9 遍历到最后 i=1，删除倒数第二个时，i=1<2要删除头节点
            return head.next;
        }
        q.next=q.next.next;
        return head;
    }

}

package leetcode.doublepointer;

import leetcode.ListNode;

/**
 * @program: offer
 * @description: 旋转链表
 * @author: xjh
 * @create: 2020-06-08 19:20
 **/
public class RotateRight {

    /**
    * @Description: 先遍历出链表的长度len，然后用k=len%k，最后求出倒数第k个元素前的元素，直接把末尾元素指向head，倒数第k个元素前的元素指向null
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/8
    */
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null){
            return null;
        }
        ListNode p=head,q=null;
        int i=0,len=1;
        while (p.next!=null){
            len++;
            p=p.next;
        }

        if (k==len){
            return head;
        }else if (k>len){
            k=k%len;
        }
        if (k==0){
            return head;
        }
        p=head;
        while (p.next!=null){
            p=p.next;
            i++;
            if (i==k){
                q=head;  //q为倒数第k个的前一个，如果len<=k，则q=null
            }else if (i>k){
                q=q.next;
            }
        }

        ListNode temp=q.next;   //temp为倒数第k个元素
        q.next=null;
        p.next=head;
        return temp;
    }
}

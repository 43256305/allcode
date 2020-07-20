package leetcode.doublepointer;

import leetcode.ListNode;

/**
 * @program: offer
 * @description: 旋转链表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 即4 5向右移动2个位置，变为了开始
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

        if (k==len){  //如果k就是链表长度或者k大于链表长度时
            return head;
        }else if (k>len){
            k=k%len;
        }
        if (k==0){  //如果k为0时
            return head;
        }
        p=head;
        while (p.next!=null){
            p=p.next;  //p指针指向最后一个节点
            i++;
            if (i==k){
                q=head;  //q为倒数第k个的前一个，如果len<=k，则q=null
            }else if (i>k){
                q=q.next;
            }
        }

        //如1 2 3 4  k=2 则q指向2，p指向4，temp指向3，2的next为null，4的next指向头：3 4 1 2 最后返回temp
        ListNode temp=q.next;   //temp为倒数第k个元素
        q.next=null;
        p.next=head;
        return temp;
    }
}

package leetcode.inorder;

import leetcode.ListNode;

/**
 * @program: offer
 * @description: K个一组反转链表
 * @author: xjh
 * @create: 2020-07-06 16:09
 **/
public class ReverseKGroup {

    /**
    * @Description:  分为三块：上一个k组，此时翻转的k组，下一个k组。我们要记录上一个k组的末尾节点（pre与end），要翻转k组的
     * 头节点（start），下一个k组的头节点（next）
     * 原理：end一直前进到第k个节点，然后记录下第k个节点的下一个节点为next，然后让第k个节点的next指向null，再把从
     * 头（start）到第k个节点通过reverse方法翻转   链表中有几个k组就要循环上面的流程多少次
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/7/6
    */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;  //指向上一个k组节点的末尾节点
        ListNode end = dummy;  //指向上一个k组节点的末尾节点->指向此时要翻转的k组节点的第k个节点

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            ListNode start = pre.next;  //指向没有翻转前的第1个节点
            ListNode next = end.next;  //指向第k个节点的下一个节点
            end.next = null;
            pre.next = reverse(start);  //上一个k组的末尾节点指向此时k组的头节点
            start.next = next;  //翻转后start就变为了当前k组的末尾节点，让末尾节点的next指向next
            pre = start;  //翻转后pre指向末尾节点

            end = pre;  //end也指向末尾节点
        }
        return dummy.next;
    }

    /**
    * @Description: 把从head到末尾的链表翻转
     * 原理：curr一直指向它的前一个pre，然后两个指针一起前进一个  返回翻转后的头节点
    * @Param:
    * @return: 
    * @Author: xjh
    * @Date: 2020/7/6
    */
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;  //next为中间指针，记录curr的后一个
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

}

package leetcode.tencentfifty;

import leetcode.ListNode;

/**
 * @program: offer
 * @description:两数相加 给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0开头。
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * @author: xjh
 * @create: 2020-06-04 21:11
 **/
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int value=l1.val+l2.val;
        int unit=value%10;
        int ten=value/10;
        ListNode head=new ListNode(unit);
        ListNode p=head;
        l1=l1.next;
        l2=l2.next;
        while (l1!=null&&l2!=null){
            value=l1.val+l2.val;
            unit=(value+ten)%10;
            ten=(value+ten)/10;
            ListNode e=new ListNode(unit);
            p.next=e;
            p=e;
            l1=l1.next;
            l2=l2.next;
        }
        while (l1!=null){
            value=l1.val+ten;
            unit=value%10;
            ten=value/10;
            ListNode e=new ListNode(unit);
            p.next=e;
            p=e;
            l1=l1.next;
        }
        while (l2!=null){
            value=l2.val+ten;
            unit=value%10;
            ten=value/10;
            ListNode e=new ListNode(unit);
            p.next=e;
            p=e;
            l2=l2.next;
        }
        if (ten!=0){
            ListNode e=new ListNode(ten);
            p.next=e;
            p=e;
        }
        return head;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;  //十位
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;   //用判断可以不用向上面一样增加while代码  x代表l1的值，y代表l2的值，如果没有值则为0
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);   //个位可以省略一个变量，直接赋值
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;   //因为头节点也需要计算，为了一个节点而多出代码量不值得，所以把头节点当成了无用节点，直接返回下一个节点
    }
}

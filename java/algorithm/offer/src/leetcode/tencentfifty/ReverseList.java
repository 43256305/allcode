package leetcode.tencentfifty;


import leetcode.ListNode;

/**
 * @program: offer
 * @description:颠倒链表
 * @author: xjh
 * @create: 2020-06-04 11:25
 **/
public class ReverseList {

    /**
    * @Description: pre总是指向新链表的头，cur总是指向旧链表的头，temp指向旧链表的头的下一个  新链表的头的next（cur.next）指向旧链表的头(pre)
     * 每次新链表增加头节点，pre指向新加的头(cur)，每次旧链表移除头节点，cur指向头节点的下一个节点(temp)
     * 时间：n，空间：1
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/4
    */
    public ListNode reverseList(ListNode head) {
        //开始时，新链表为null
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while(cur!=null) {
            //记录当前节点的下一个节点
            tmp = cur.next;
            //然后将当前节点的next指向pre
            cur.next = pre;
            //pre和cur节点都前进一位
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    
    /**
    * @Description: 递归法，时间空间都是n，不推荐
     * 从最后面退出递归，把链表后面的元素逐个指向前一个，如1->2->3->4->5   最后p=5,head=4时退出递归，执行代码，4.next=5，5的next指向4，4的next指向null
     * 1->2->3->4->null，5->4，返回5. 下一个递归则对p=5，head=3颠倒，p=5，head=2颠倒，最后返回p=5的头节点。（这里面p一直指向最后的节点）
     * （每一个递归的操作：5->4->null 4->3->null 3->2->null 2->1->null ）
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/4
    */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;  //递归退出
        ListNode p = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public void headInsert(ListNode head){   //两个指针（head，e）  头插法
        for (int i=0;i<10;i++){
            ListNode e=new ListNode(i);

            e.next=head.next;
            head.next=e;
        }
    }

    public void tailInsert(ListNode head){   //两个指针（p，e），p指向尾节点  尾插法
        ListNode p=head;
        for (int i=0;i<10;i++){
            ListNode e=new ListNode(i);
            e.next=null;

            p.next=e;
            p=e;
        }
    }
}


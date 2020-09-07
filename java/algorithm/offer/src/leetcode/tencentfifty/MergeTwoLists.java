package leetcode.tencentfifty;

import leetcode.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @program: offer
 * @description: 合并两个有序链表
 * @author: xjh
 * @create: 2020-06-06 15:37
 **/
public class MergeTwoLists {
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead=new ListNode(0),q=newHead,temp=null;
        while (l1!=null&&l2!=null){
            if (l1.val>l2.val){
                temp=l2.next;
                q.next=l2;
                q=l2;
                l2=temp;
            }else{
                temp=l1.next;
                q.next=l1;
                q=l1;
                l1=temp;
            }
        }
        if (l1!=null){  //最后退出循环时，q.next指向的一定是null，所以还要判断l1和l2是否为null
            q.next=l1;
        }else{
            q.next=l2;
        }
        return newHead.next;
    }

    /**
    * @Description: 递归  比较快
     * 终止条件：l1为null或者l2为null时  返回值：每一层调用都返回排序好的链表头
     * 递归内容：如果l1的val值更小，则将 l1.next 与排序好的链表头相接，l2 同理
     * 其实递归就是程序内部维护了一个栈。这个题就是每次都把最小值压入栈，最后出栈的时候，将所有数连在一起就可以了。说白了，就是用一个
     * 栈维护了顺序。最后的连接，当然是小的连小的，所以l1 小，就连到 l1,l2 小就连到 l2，最后先返回的，就是最小的头结点。
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/6
    */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        if(l1.val < l2.val) {
            l1.next = mergeTwoLists1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists1(l1, l2.next);
            return l2;
        }
    }

    /**
    * @Description: 合并k个链表   暴力法  n（数组）*m（最长链表）
     * 每次比较k个链表的第一个元素，选出最小的，然后把最小的加入总链表并且最小的链表向前一位
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/6
    */
    public ListNode mergeKLists(ListNode[] lists) {
        int minIndex=Integer.MAX_VALUE;
        int minValue=Integer.MAX_VALUE-1;
        int len=lists.length;
        ListNode head=new ListNode(0);
        ListNode q=head;
        ListNode[] list=new ListNode[len];
        for (int i=0;i<len;i++){
            list[i]=lists[i];
        }
        while (minValue!=Integer.MAX_VALUE){
            minValue=Integer.MAX_VALUE;
            for (int i=0;i<len;i++){
                int val=list[i]==null ? Integer.MAX_VALUE:list[i].val;
                if (val<minValue){
                    minValue=val;
                    minIndex=i;
                }
            }
            if (minValue!=Integer.MAX_VALUE){
                q.next=list[minIndex];
                q=list[minIndex];
                list[minIndex]=list[minIndex].next;
            }
        }

        return head.next;
    }

    /**
    * @Description: 优先队列   时间复杂度：n*log（k）  n为所有节点，k为链表个数
     * 先入队所有链表第一个，然后每出一个，进入一个
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/6
    */
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) return -1;
                else if (o1.val == o2.val) return 0;
                else return 1;
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        for (ListNode node : lists) {
            if (node != null) queue.add(node);
        }
        while (!queue.isEmpty()) {
            p.next = queue.poll();
            p = p.next;
            if (p.next != null) queue.add(p.next);  //每一个add的地方都要判断是否为null
        }
        return dummy.next;
    }


    /**
    * @Description: 分治法  把链表用递归两两合并（归并：已知部分的最优解，如何应用在整体上？）
     * 与归并排序不同的地方就是当start==end时，是直接返回lists[start]，也就是说，当start==end时，就是一个链表，所以直接返回此链表
     * merge有两个返回的地方，一个是返回合并后的头，一个是直接返回list[start]，前一个返回针对的是当数组中有两个链表时，后一个返回针对的是
     * 数组中只有一个链表，所以返回此链表即可
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/6/6
    */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];    //唯一与归并排序不同的地方   left==right说明left与right就是一条链表，不用合并，直接返回
        int mid = left + (right - left) / 2;   //求right与left中间位置，（left+right）/2的变形   （right-left+1）/2求的是长度的一半
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists2(l1, l2);
    }

    private ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1,l2.next);
            return l2;
        }
    }

}

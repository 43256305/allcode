package other;

import java.util.*;

/**
 * @program: offer
 * @description:一遍找出单向链表的倒数第k个节点
 * 我们可以定义两个指针。第一个指针从链表的头指针开始遍历向前走k-1，第二个指针不动；从第k步开始，第二个指针也开始从链表头指针开始遍历。
 * 由于两个指针的距离保持在k-1，当第一个指针到达链表的尾结点时，第二个指针正好是倒数第k个结点。
 * @author: xjh
 * @create: 2020-04-04 20:57
 **/
public class FindKToTail {
    public static void main(String[] args) {
        Integer[] array={1,2,3,4,5,6,7,8,9};
        ArrayList<Integer> list= new ArrayList<>(Arrays.asList(array));
        getK(list,4);
    }

    /**
    * @Description:
     * 变量：i（代表相隔距离，一直到k为止）
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/5/25
    */
    public static void getK(ArrayList<Integer> list, int k){
        if (list.size()<k){
            System.out.println("没有倒数第k个节点");
            return;
        }
        //由于java中list没有next方法，所以用两个迭代器
        Iterator iterator=list.iterator();
        Iterator iterator1=list.iterator();
        int node=Integer.MAX_VALUE;
        int i=1;
        while (iterator.hasNext()){
            if (i<k){   //指针1要多走k-1步（因为倒数第一个和倒数第k个之间就是差了k-1，如5，4，3，2，1中倒数第3个与倒数第1个就是差了3-1=2）
                iterator.next();
                i++;
            }else{  //从第k步开始
                node=(int)iterator1.next();
                iterator.next();
            }
        }
        System.out.println(node);
    }

}

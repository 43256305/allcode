package leetcode.inorder;

import java.util.*;

/**
 * @program: offer
 * @description: LRU缓存机制
 * @author: xjh
 * @create: 2020-07-07 11:08
 **/


class Solution1{
    
    /**
    * @Description: 使用LinkedHashMap(双向链表)
     * 设置LinkedHashMap的accessOrder为true，我们每次getOrDefault或者get或者put时，都会将访问的node节点移动到队尾。简而言之，就是如果
     * accessOrder为false（默认）时，就是按照插入顺序排列，为true时则为按照访问顺序排列。（设置为true就是实现了lru）
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/7/18
    */
    class LRUCache extends LinkedHashMap<Integer, Integer>{
        private int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {   //当size大于容量时，要返回true
            //在LinkedHashMap内部，每次插入都会调用此方法
            return size() > capacity;
        }
    }
}

class Solution2{

    /**
    * @Description: 双向链表
     * 双向链表，一个dummyhead，一个dummynext
     * get：先从链表删除此节点，再把此节点加入到dummyhead的next
     * put：判断此节点是否存在，不存在则放入map，放入链表头，如果满了，则去除dummytail的pre  如果存在，则加入改变value值，放入dummyhead的next
    * @Param:
    * @return:
    * @Author: xjh
    * @Date: 2020/7/18
    */
    public class LRUCache {
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;
            public DLinkedNode() {}
            public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
        }

        private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            // 使用伪头部和伪尾部节点
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {   //没有判断是否存在，而是直接声明，再判断是否为null
                return -1;
            }
            // 如果 key 存在，先通过哈希表定位，再移到头部
            moveToHead(node);   //先删除node节点，再把node加入到头节点的下一个
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                // 如果 key 不存在，创建一个新的节点
                DLinkedNode newNode = new DLinkedNode(key, value);
                // 添加进哈希表
                cache.put(key, newNode);
                // 添加至双向链表的头部
                addToHead(newNode);
                ++size;
                if (size > capacity) {
                    // 如果超出容量，删除双向链表的尾部节点
                    DLinkedNode tail = removeTail();
                    // 删除哈希表中对应的项
                    cache.remove(tail.key);
                    --size;
                }
            }
            else {
                // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
                node.value = value;
                moveToHead(node);
            }
        }

        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }
    }
}

//自己写的  1.外部类包含伪造的头和尾，大小，容量，map  2.内部类（双向链表）包含next，pre，key，value
//3.主要操作：移动到头节点，删除尾节点，增加到头节点  4.get与put每一次都先根据key获取node
class Solution3{
    public class LRUCache {
        private HashMap<Integer, Node> map = new HashMap();
        private int threshold ;
        private int size;
        private Node head;   //伪头节点与尾节点，可以简化代码的判断
        private Node tail;

        class Node{
            Node pre;
            Node next;
            Integer value;
            Integer key;

            public Node(Integer key, Integer value){
                this.key=key;
                this.value=value;
            }
        }

        public LRUCache(){

        }

        public LRUCache(int threshold){
            this.threshold = threshold;
            this.size = 0;
            head = new Node(null,null);
            tail = new Node(null,null);
            head.next=tail;
            tail.pre=head;
        }

        public int get(Integer key){
            Node node = map.get(key);
            if (node!=null){
                moveToHead(node);
                return node.value;
            }
            return -1;
        }

        public void put(Integer key, Integer value){
            Node node = map.get(key);
            if (node!=null){  //存在此节点
                node.value=value;
                moveToHead(node);
            }else{  //不存在
                Node node1 = new Node(key,value);
                addToHead(node1);
                map.put(key,node1);
                size++;
                if (size>threshold){
                    removeTail();
                    size--;
                }
            }
        }

        public void moveToHead(Node node){
            node.pre.next=node.next;  //原来位置的pre与next
            node.next.pre=node.pre;
            node.next=head.next;  //现在位置的pre与next
            node.pre=head;
            head.next=node; //头节点的next
            node.next.pre=node;  //头节点下一个节点的pre
        }

        public void addToHead(Node node){
            node.next=head.next;  //现在位置的pre与next
            node.pre=head;
            head.next=node; //头节点的next
            node.next.pre=node;  //头节点下一个节点的pre
        }

        public void removeTail(){
            Node node = tail.pre;  //真实的尾节点
            node.pre.next=tail;
            tail.pre=node.pre;
            map.remove(node.key);  //注意维护map
        }
    }
}
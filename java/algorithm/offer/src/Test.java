import leetcode.ListNode;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @program: offer
 * @description:
 * @author: xjh
 * @create: 2020-06-15 08:41
 **/
public class Test {
    static Test test = new Test();
    public static void main(String[] args) {
//        int[] nums = {3,2,5,7,8,1};
//        test.heapSort(nums);
        Consumer consumer = new Consumer(10);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<100;i++){
                    consumer.produce(i);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<100;i++){
                    System.out.println(consumer.consume());
                }
            }
        }).start();
    }

    public void heapSort(int[] nums){
        for (int i=nums.length/2;i>=0;i--){
            adjust(nums,i,nums.length);
        }

        for (int i = nums.length - 1;i>=0;i--){
            swap(nums,i,0);
            adjust(nums,0,i);
        }
        System.out.println(Arrays.toString(nums));
    }

    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a]=nums[b];
        nums[b]=temp;
    }

    public void adjust(int[] nums, int i, int n){
        int child=getLeft(i);
        int father;
        for (father=nums[i];getLeft(i)<n;i=child){
            child=getLeft(i);
            if (child+1<n&&nums[child]<nums[child+1]){
                child++;
            }
            if (father<nums[child]){
                nums[i]=nums[child];
            }else{
                return;
            }
        }
        nums[i]=father;
    }


    public int getLeft(int i){
        return i*2+1;
    }

    public void minK(int[] nums,int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2){
                    return -1;
                }else if (o1 == o2){
                    return 0;
                }else{
                    return 1;
                }
            }
        });
        for (int num:nums){
            queue.add(num);
        }
        for (int i = 0;i<k;i++){
            System.out.println(queue.poll());
        }
    }

}

class Consumer{
    private int[] nums;
    private Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    private int capacity;
    private int size;
    private int index;
    public Consumer(){};
    public Consumer(int capacity){
        nums = new int[capacity];
        this.capacity = capacity;
        size = 0;
        index = 0;
    }
    public void produce(int num){
        try{
            lock.lock();
            while (size == capacity){
                notEmpty.await();
            }
            nums[index] = num;
            index++;
            size++;
            notFull.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public int consume(){
        try{
            lock.lock();
            while (size == 0){
                notFull.await();
            }
            index--;
            size--;
            notEmpty.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return nums[index];
    }
}
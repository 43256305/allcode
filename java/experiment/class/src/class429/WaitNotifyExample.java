package class429;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-04-29 09:52
 **/
public class WaitNotifyExample {
    public static Object o = new Object();// 锁

    public static void main(String[] args) {
        for(int i = 0;i < 5;i++){
            ThreadA ta = new ThreadA(i + 1);
            ta.start();
        }
        ThreadB tb = new ThreadB();
        tb.start();
    }
}

class ThreadA extends Thread {
    private int id;

    public ThreadA(int id) {
        super();
        this.id = id;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        synchronized (WaitNotifyExample.o) {
            System.out.println( id + "A enter synchronized...");
            try {
                System.out.println(id + "A wait...");
                WaitNotifyExample.o.wait();
                System.out.println(id + "A is notified...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(id + "A leave synchronized...");
    }
}

class ThreadB extends Thread {
    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        System.out.println("B enter sychronized...");
        synchronized (WaitNotifyExample.o) {
            WaitNotifyExample.o.notifyAll();
            System.out.println("B is notified...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("B leave synchronized...");
    }

}

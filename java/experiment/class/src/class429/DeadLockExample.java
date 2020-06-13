package class429;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-04-29 09:52
 **/
public class DeadLockExample extends Thread {
    private Lock lock1;
    private Lock lock2;
    private int id;

    public DeadLockExample(Lock lock1, Lock lock2, int id) {
        super();
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.id = id;
    }

    public static void main(String[] args) {
        Lock lock1 = new Lock("lock1");
        Lock lock2 = new Lock("lock2");
        Thread t1 = new DeadLockExample(lock1, lock2, 1);
        Thread t2 = new DeadLockExample(lock1, lock2, 2);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        synchronized (lock1) {
            System.out.printf("%s: gets %s \n", id,lock1.getName());
            synchronized (lock2) {
                System.out.printf("%s: gets %s \n", id,lock2.getName());
            }
            System.out.printf("%s: releases %s \n", id,lock2.getName());
        }
        System.out.printf("%s: releases %s \n", id,lock1.getName());
    }


}

class Lock{
    private String name;

    public Lock(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

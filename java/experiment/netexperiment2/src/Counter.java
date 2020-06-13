import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: netexperiment2
 * @description:
 * @author: xjh
 * @create: 2020-04-29 16:39
 **/
public class Counter {
    private int c = 30;
    public  void decrement() {
        if(c>0)
            c--;
        System.out.println(Thread.currentThread()+"="+c);
    }

    static class Main{
        public static void main(String[] args) {
            ExecutorService pool=Executors.newSingleThreadExecutor();
            Counter c=new Counter();
            for (int i=0;i<300;i++){
                pool.execute(()->{
                    c.decrement();
                });
            }
            pool.shutdown();
        }
    }
}


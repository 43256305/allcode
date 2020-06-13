/**
 * @program: netexperiment2
 * @description:
 * @author: xjh
 * @create: 2020-04-29 16:22
 **/
public class ex2 {
    public static void main(String[] args) {
        for (int i=0;i<3;i++){
            new ProduceThread1().start();
//            new Thread(new ProduceThread2()).start();
        }
    }

    static class ProduceThread1 extends Thread{
        @Override
        public void run() {
            for (int i=1;i<11;i++){
                System.out.println(i);
            }
        }
    }

    static class ProduceThread2 implements Runnable{
        @Override
        public void run() {
            for (int i=1;i<11;i++){
                System.out.println(i);
            }
        }
    }
}

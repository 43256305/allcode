import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: netexperiment2
 * @description:
 * @author: xjh
 * @create: 2020-04-29 16:17
 **/
public class ex1 {
    public static void main(String[] args) {
        ExecutorService pool=Executors.newCachedThreadPool();
        System.out.println("请输入线程数（只能为整数）：");
        Scanner in=new Scanner(System.in);
        int size=in.nextInt();
        for (int i=0;i<size;i++){
            pool.execute(()->{
                System.out.println(Thread.currentThread().getName());
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();
    }
}

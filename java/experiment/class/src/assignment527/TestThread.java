package assignment527;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-05-27 11:07
 **/
public class TestThread {
    private static int i=0;
    public static void main(String[] args) {
        ServerSocket ss=null;
        Socket s=null;
        try{
            ss=new ServerSocket(9999);
            while (true){
                s=ss.accept();
                i++;
                System.out.println("新的客户端连接:"+i);
                try{
                    Thread.sleep(10000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

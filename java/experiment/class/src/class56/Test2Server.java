package class56;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-05-06 09:06
 **/
public class Test2Server {
    public static void main(String[] args) {
        try{
            ServerSocket ss=new ServerSocket(3456);
            while (true){
                System.out.println("listen");
                Socket s=ss.accept();
                System.out.println(ss.getLocalPort());
                System.out.println(s.getLocalPort());
                System.out.println(s.getPort());
                System.out.println(s.getLocalAddress());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

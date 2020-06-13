package class56;

import java.net.InetAddress;
import java.net.Socket;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-05-06 09:06
 **/
public class Test2Client {
    public static void main(String[] args) {
        Socket s;
        try{
            for (int i=0;i<3;i++){
                s=new Socket(InetAddress.getByName("localhost"),3456);
                System.out.println(s.getLocalPort()+","+s.getPort());
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

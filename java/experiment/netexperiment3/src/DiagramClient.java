import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

/**
 * @program: netexperiment3
 * @description:
 * @author: xjh
 * @create: 2020-05-24 17:06
 **/
public class DiagramClient {
    public static void main(String[] args) {
        Random random=new Random();
        DatagramSocket ds = null;
        try{
            ds = new DatagramSocket();
            while (true){
                String str = String.valueOf(random.nextInt(45));
                DatagramPacket dp = new DatagramPacket(str.getBytes(), 0, str.length(),
                        InetAddress.getByName("localhost"), 9999);
                ds.send(dp);//发送数据
                System.out.println(str);
                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ds.close();
        }

    }
}

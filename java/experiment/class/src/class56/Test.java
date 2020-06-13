package class56;

import java.net.InetAddress;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-05-06 08:23
 **/
public class Test {
    public static void main(String[] args) {
        String host;
        host="www.baidu.com";
        try{
            InetAddress address=InetAddress.getByName(host);
            System.out.println(address);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

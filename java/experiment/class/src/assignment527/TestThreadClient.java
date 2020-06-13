package assignment527;

import java.net.Socket;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-05-27 11:07
 **/
public class TestThreadClient {
    public static void main(String[] args) {
        Socket s=null;
        try{
            while(true) {
                s=new Socket("localhost",9999);
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

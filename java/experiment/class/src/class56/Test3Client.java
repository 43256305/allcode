package class56;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-05-06 09:24
 **/
public class Test3Client {
    public static void main(String[] args) {
        Socket s;
        try{
            s=new Socket(InetAddress.getByName("localhost"),3456);
            PrintWriter pw=new PrintWriter(s.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
            Scanner in=new Scanner(System.in);
            pw.println(in.nextLine());
            pw.println(in.nextLine());
            pw.flush();   //注意用完了写flush
            if (br.readLine().equals("T")){
                System.out.println("登录成功");
            }else{
                System.out.println("登录失败");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

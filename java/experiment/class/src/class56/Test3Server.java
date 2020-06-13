package class56;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-05-06 09:25
 **/
public class Test3Server {
    public static void main(String[] args) {
        ServerSocket ss;
        Socket s;
        BufferedReader br;
        PrintWriter pw;
        try{
            ss=new ServerSocket(3456);
            while (true){
                s=ss.accept();
                br=new BufferedReader(new
                        InputStreamReader(s.getInputStream()));
                pw=new PrintWriter(s.getOutputStream());   //BufferedWriter没有PrintWriter使用灵活
                if (br.readLine().equals("user")&&br.readLine().equals("password")){
                    pw.println("T");
                    System.out.println("user is valid");
                }else{
                    pw.println("F");
                    System.out.println("user is invalid");
                }
                pw.flush();  //注意写flush
                s.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

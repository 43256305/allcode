package class513;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-05-13 09:36
 **/
public class Test {
    public static void main(String[] args) {
        try{
            Socket s=new Socket("dict.org",2628);
            BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println(br.readLine());
            PrintWriter pw=new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            pw.println("define fd-eng-lat apple");
            pw.flush();
            System.out.println(br.readLine());
            System.out.println(br.readLine());
            System.out.println(br.readLine());
            System.out.println(br.readLine());
            System.out.println(br.readLine());
            br.close();
            s.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

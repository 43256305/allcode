package assignment527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-05-27 11:04
 **/
public class Server {
    private static Socket s = null;

    public static void main(String[] args) {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(40000);
            while (true) {
                s=ss.accept();
                new Thread(()->{
                    try{
                        BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
                        String str=br.readLine();
                        String[] strings=str.split(",");
                        double sum=0;
                        for (int i=0;i<strings.length;i++){
                            sum+=Integer.valueOf(strings[i]);
                        }
                        StringBuilder result=new StringBuilder("总数："+sum+" ");
                        double avg=sum/strings.length;
                        result.append("平均数："+avg+" ");
                        double sum1=0;
                        for (int i=0;i<strings.length;i++){
                            sum1+=Math.pow(Integer.valueOf(strings[i])-avg,2);
                        }
                        result.append("方差："+sum1/strings.length);
                        PrintWriter pw=new PrintWriter(s.getOutputStream());
                        pw.println(result);
                        pw.flush();

                        //flush()方法作用是强制将缓冲区的数据输出。
                        //因为输出时，只有等到缓冲区满了才输出，有些时候输出的内容比较少的时候，没有充满缓冲区，不会立刻输出的。
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }).start();
            }
            }catch(Exception e){
                e.printStackTrace();
            }
    }
}

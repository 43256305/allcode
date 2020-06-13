package manage;

import data.Message;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: nettermproject
 * @description: 管理后台
 * @author: xjh
 * @create: 2020-06-03 10:54
 **/
public class ManageServer extends Application {
    //多个终端写入hashmap与list，服务端和搜索端也在读取，所以需要并发容器
    private static ConcurrentHashMap<String, List<Message>> map=new ConcurrentHashMap<>();
    private static double sumTem=0;
    private static double sumHu=0;
    private static String exist="";
    private static SocketAddress socketAddress;

    @Override
    public void start(Stage primaryStage) throws Exception {
        CyclicBarrier barrier=new CyclicBarrier(2);
        new Thread(()->{
            readMessage();
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            receiveMessage();
        }).start();

        VBox vBox=new VBox();
        TextArea textArea=new TextArea();
        textArea.setDisable(true);
        textArea.setStyle("-fx-control-inner-background:red;");
        textArea.setPrefHeight(500);
        textArea.appendText("江西各地平均气温，平均湿度(每10秒更新一次)："+"\n");
        vBox.getChildren().addAll(textArea);
        new Thread(()->{
            try {
                barrier.await();  //上面的读取完成这里才能计算平均值
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            while (true){
                for (String s:map.keySet()){
                    List list=map.get(s);
                    for (int i=0;i<list.size();i++){
                        sumTem+=((Message)list.get(i)).getTemperature();
                        sumHu+=((Message)list.get(i)).getHumidity();
                    }
                    Platform.runLater(()->{
                        if (!textArea.getText().contains(s)){
                            textArea.appendText(s+"  平均温度:"+sumTem/list.size()+"  平均湿度："+sumHu/list.size()+"\n");
                        }else{
                            textArea.setText(textArea.getText().replaceAll(s+"(.*?)\n",s+"  平均温度:"+sumTem/list.size()+"  平均湿度："+sumHu/list.size()+"\n"));
                        }
                        try {
                            barrier.await();
                            sumHu=0;
                            sumTem=0;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    });
                    try {
                        barrier.await();   //等到上一个线程的sum赋值为0，次线程才能开始计算
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Scene scene=new Scene(vBox,500,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("管理后台");
        primaryStage.show();
    }

    public static void receiveMessage(){
        DatagramSocket socket=null;
        try{
            socket=new DatagramSocket(9999);
            byte[] buff=new byte[1024];
            DatagramPacket packet=new DatagramPacket(buff,1024);
            while (true){
                socket.receive(packet);
                String s=new String(packet.getData(),0,packet.getLength());
                if (s.contains("city")){
                    String city=s.split(":")[1];
                    exist=city;
                    socketAddress=packet.getSocketAddress();
                }else{
                    Message message=Message.toMessageObject(s);
                    new Thread(new MultiHandle(message)).start();
                    //每接收到一条数据，判断是否为搜索端查看的城市，是则发送给搜索端
                    if (exist.equals(message.getAddress())){
                        DatagramPacket sendPacket=new DatagramPacket(s.getBytes(),s.getBytes().length,socketAddress);
                        socket.send(sendPacket);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (socket!=null){
                socket.close();
            }
        }
    }

    public static void readMessage(){
        BufferedReader br=null;
        try{

            File file=new File("file");
            if (file.isDirectory()){
                File[] list=file.listFiles();
                for (int i=0;i<list.length;i++){
                    CopyOnWriteArrayList<Message> arrayList=new CopyOnWriteArrayList<>();
                    map.put(list[i].getName().replace(".txt",""),arrayList);
                    br=new BufferedReader(new InputStreamReader(new FileInputStream(file.getName()+"/"+list[i].getName())));
                    String s=br.readLine();
                    while (s!=null){
                        arrayList.add(Message.toMessageObject(s));
                        s=br.readLine();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (br!=null){
                try{
                    br.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
    * @Description: 把传过来的一条message写入文件
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/6/6
    */
    static class MultiHandle implements Runnable{
        private Message message;

        public MultiHandle(Message message){
            this.message=message;
        }

        @Override
        public void run() {
            if (map.containsKey(message.getAddress())){
                map.get(message.getAddress()).add(message);
            }else{
                CopyOnWriteArrayList list=new CopyOnWriteArrayList();
                list.add(message);
                map.put(message.getAddress(),list);
            }
            try{
                FileWriter fw=new FileWriter("file/" +message.getAddress()+".txt",true);
                PrintWriter pw=new PrintWriter(fw);
                pw.println(message);
                pw.flush();
                pw.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

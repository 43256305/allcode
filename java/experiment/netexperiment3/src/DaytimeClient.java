import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: netexperiment3
 * @description:
 * @author: xjh
 * @create: 2020-05-18 17:12
 **/
public class DaytimeClient extends Application {
    public static void main(String[] args) {
        launch();
    }

    public static void getTime(){
        Socket s;
        try{
            s=new Socket("localhost",2007);
            DataInputStream dis=new DataInputStream(s.getInputStream());
            readTime(dis);
            dis.close();
            s.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void readTime(DataInputStream in) throws IOException {
        System.out.println(in.readInt());
        System.out.println(in.readByte()+1);
        System.out.println(in.readByte());
        System.out.println(in.readByte());
        System.out.println(in.readByte());
        System.out.println(in.readByte());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBox=new HBox();
        Button btn1=new Button();
        Button btn2=new Button();
        Button btn3=new Button();
        Button btn4=new Button();
        Button btn5=new Button();
        Button btn6=new Button();
        hBox.getChildren().addAll(btn1,btn2,btn3,btn4,btn5,btn6);
        hBox.setSpacing(20);
        new Thread(()->{
            Socket s;
            CyclicBarrier cyclicBarrier=new CyclicBarrier(2);
            while (true){
                try{
                    DataInputStream dis;
                    s=new Socket("localhost",2007);
                    dis=new DataInputStream(s.getInputStream());
                    Platform.runLater(()->{
                        try{
                            btn1.setText(String.valueOf(dis.readInt()));
                            btn2.setText(String.valueOf(dis.readByte()+1));
                            btn3.setText(String.valueOf(dis.readByte()));
                            btn4.setText(String.valueOf(dis.readByte()));
                            btn5.setText(String.valueOf(dis.readByte()));
                            btn6.setText(String.valueOf(dis.readByte()));
                            cyclicBarrier.await();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    });
                    cyclicBarrier.await();
                    dis.close();
                    s.close();
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        Scene scene=new Scene(hBox,350,50);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

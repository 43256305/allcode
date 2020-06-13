package collection;

import data.Message;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

/**
 * @program: nettermproject
 * @description:
 * @author: xjh
 * @create: 2020-06-05 13:49
 **/
public class ColleClientGA extends Application {
    private static String address="高安";
    private static volatile boolean start=false;
    private static TextArea textArea=new TextArea();

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox=new VBox();
        HBox hBox=new HBox();
        Button start=new Button("开始收集");
        start.setOnAction((event)->{
            ColleClientGA.setStart(true);
            new Thread(()->{
                sendMessage();
            }).start();
        });
        Button end=new Button("终止收集");
        end.setOnAction((event)->{
            ColleClientGA.setStart(false);
        });
        hBox.getChildren().addAll(start,end);
        vBox.getChildren().addAll(hBox);
        textArea.setDisable(true);
        textArea.setPrefHeight(450);
        textArea.setStyle("-fx-control-inner-background:red;");
        textArea.textProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue,
                                Object newValue) {
                textArea.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
                //use Double.MIN_VALUE to scroll to the top
            }
        });
        vBox.getChildren().addAll(textArea);
        Scene scene=new Scene(vBox,500,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("高安");
        primaryStage.show();
    }

    public static void sendMessage(){
        DatagramSocket socket=null;
        try{
            socket=new DatagramSocket();
            while (start){
                String message=randEnviroment();
                byte[] buff=message.getBytes();
                DatagramPacket packet=new DatagramPacket(buff,0,buff.length, InetAddress.getByName("localhost"),9999);
                socket.send(packet);
                Message message1=Message.toMessageObject(message);
                Platform.runLater(()->{
                    textArea.appendText(message1.toReadableString()+"\n");
                });
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
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

    public static String randEnviroment(){
        Random random=new Random();
        float temperature=random.nextFloat()*50-10;
        double humidity=random.nextFloat()*0.6+0.2;

        return String.format("%s %s %.1f %.1f",address,System.currentTimeMillis(),temperature,humidity);
    }

    public static boolean isStart() {
        return start;
    }

    public static void setStart(boolean start) {
        ColleClientGA.start = start;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

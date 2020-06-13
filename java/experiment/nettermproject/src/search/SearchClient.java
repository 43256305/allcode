package search;

import data.Message;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @program: nettermproject
 * @description: 查询终端  查询已有城市的实时环境
 * @author: xjh
 * @create: 2020-06-03 10:54
 **/
public class SearchClient extends Application {
    private static DatagramSocket socket=null;
    private static TextArea textArea=new TextArea();

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField textField=new TextField("请输入城市名字");
        VBox vBox=new VBox();
        Button button=new Button("查询");
        HBox hBox=new HBox();
        hBox.getChildren().addAll(button);
        hBox.setAlignment(Pos.CENTER);
        textArea.setPrefHeight(400);
        textArea.setDisable(true);
        textArea.setStyle("-fx-control-inner-background:red;");
        try{
            socket=new DatagramSocket();
        }catch (Exception e){
            e.printStackTrace();
        }
        button.setOnAction((event)->{
            try {
                byte[] city=("city:"+textField.getText()).getBytes();
                DatagramPacket packet=new DatagramPacket(city,city.length, InetAddress.getByName("localhost"),9999);
                socket.send(packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Thread(()->{
                receiveMessage();
            }).start();
        });
        vBox.getChildren().addAll(textField,hBox,textArea);
        Scene scene=new Scene(vBox,500,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("搜索界面");
        primaryStage.show();
    }

    public static void receiveMessage(){
        byte[] buff=new byte[1024];
        DatagramPacket packet=new DatagramPacket(buff,1024);
        while (true){
            try{
                socket.receive(packet);
                Message message=Message.toMessageObject(new String(packet.getData(),0,packet.getLength()));
                Platform.runLater(()->{
                    textArea.appendText(message.toReadableString()+"\n");
                });
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

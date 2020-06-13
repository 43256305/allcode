package assignment527;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * @program: class
 * @description:
 * @author: xjh
 * @create: 2020-05-27 11:03
 **/
public class Client extends Application {
    private Socket socket=null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox=new VBox();
        TextArea textArea=new TextArea("输入数字，以逗号隔开！");
        Button button=new Button("计算");
        vBox.getChildren().add(textArea);
        vBox.getChildren().add(button);
        Text text=new javafx.scene.text.Text();
        vBox.getChildren().add(text);
        button.setOnAction((event)->{
            new Thread(()->{
                try{
                    socket=new Socket("localhost",40000);
                    PrintWriter pw=new PrintWriter(socket.getOutputStream());
                    pw.println(textArea.getText());
                    pw.flush();
                    BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    Platform.runLater(()->{
                        try{
                            text.setText(br.readLine());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        });
        Scene scene=new Scene(vBox,500,300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

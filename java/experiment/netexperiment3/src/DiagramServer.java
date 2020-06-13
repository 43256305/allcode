import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: netexperiment3
 * @description:
 * @author: xjh
 * @create: 2020-05-24 17:06
 **/
public class DiagramServer extends Application {
    private DatagramSocket ds=null;
    private XYChart.Series<Number, Number> series ;
    private int flag=0;
    private XYChart.Series<Number, Number> avg =new XYChart.Series<>();
    private int size;
    private XYChart.Series<Number, Number> seriesSize ;
    private int i=0;
    private int sum=0;

    @Override
    public void start(Stage stage) throws Exception {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("时间");
        yAxis.setLabel("温度");

        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(
                xAxis, yAxis);

        lineChart.setTitle("温度曲线图");
        Map<String,XYChart.Series<Number, Number>> map=new HashMap();

        try{
            ds= new DatagramSocket(9999);//创建DatagramSocket指定闆
            byte[] buff = new byte[1024];//创建byte数组
            DatagramPacket dp = new DatagramPacket(buff, 1024);//创建数据包对象
            new Thread(()->{
                while (true){
                    try{
                        ds.receive(dp);//接收消息
                        String str = new String(dp.getData(), 0, dp.getLength());//得到数据
                        System.out.println(str);
                        if (!map.containsKey(dp.getSocketAddress().toString())){
                            series=new XYChart.Series<Number, Number>();
                            map.put(dp.getSocketAddress().toString(),series);
                            flag=1;
                            if (size==0){
                                size++;
                                seriesSize=series;
                            }else{
                                size++;
                            }
                        }else{
                            series=map.get(dp.getSocketAddress().toString());
                        }
                        Platform.runLater(()->{
                            i++;
                            if (size>=2){
                                sum+=Integer.valueOf(str);
                            }
                            if (flag==1){
                                lineChart.getData().add(series);
                                flag=0;
                            }
                            if(series==seriesSize){
                                series.getData().add(new XYChart.Data<Number, Number>(seriesSize.getData().size()+1, Integer.valueOf(str)));
                            }else{
                                series.getData().add(new XYChart.Data<Number, Number>(seriesSize.getData().size(), Integer.valueOf(str)));
                            }
                        });
                        if (size>=2&&i%size==0){
                            Platform.runLater(()->{
                                if (!lineChart.getData().contains(avg)){
                                    lineChart.getData().add(avg);
                                }
                                avg.getData().add(new XYChart.Data<Number, Number>(seriesSize.getData().size(), sum/size));
                                sum=0;
                            });
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }catch (Exception e){
            e.printStackTrace();
        }

        Scene scene = new Scene(lineChart, 1600, 600);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

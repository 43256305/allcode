package data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: nettermproject
 * @description:
 * @author: xjh
 * @create: 2020-06-03 17:04
 **/
public class Message {
    private String address;  //地址
    private Long timestamp;  //时间
    private float temperature;  //温度
    private float humidity;  //湿度
    private String time;   //可读时间
    private static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Message(String address, Long timestamp, float temperature, float humidity) {
        this.address = address;
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.humidity = humidity;
        this.time=format.format(new Date(timestamp));
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.1f %.1f",address,timestamp,temperature,humidity);
    }

    public static Message toMessageObject(String s){
        String[] message=s.split(" ");
        return new Message(message[0],Long.valueOf(message[1]),Float.valueOf(message[2]),Float.valueOf(message[3]));
    }

    public String toReadableString(){
        return "地点："+address+" 时间："+this.getTime()+" 温度:"+
                this.getTemperature()+" 湿度："+this.getHumidity();
    }
}

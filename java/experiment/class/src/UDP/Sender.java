package UDP;

import java.io.IOException;
import java.net.*;

/**
 * @program: datastructure
 * @description:
 * @author: xjh
 * @create: 2020-05-27 10:05
 **/
public class Sender {
    public static void main(String[] args) {
        int remotePort = 40000 ;
        DatagramSocket socket = null;
        DatagramPacket packet = null;
        InetAddress ipAddr = null;

        try{socket = new DatagramSocket();}
        catch (SocketException ex) {}

        try {
            ipAddr=InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {}
        for (int i = 0; i < 5; i++) {

            String msg = "17201324 谢杰辉: "+ i;
            byte [] data = msg.getBytes();

            packet = new DatagramPacket(data,
                    data.length, ipAddr, remotePort);

            try { socket.send(packet); }
            catch (IOException e) {}

        } // for

    }
}

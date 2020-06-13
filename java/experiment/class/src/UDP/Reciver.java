package UDP;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @program: datastructure
 * @description:
 * @author: xjh
 * @create: 2020-05-27 10:06
 **/
public class Reciver {
    public static void main(String[] args) {
        int myPort = 40000;
        byte[] buf = new byte[64];
        DatagramPacket packet = new
                DatagramPacket(buf,
                buf.length);

        DatagramSocket socket = null;

        try {
            socket = new
                    DatagramSocket(myPort);
            socket.setSoTimeout(10000);
        } catch (SocketException se){}
        boolean endOfCom = false;
        do {
            try {
                socket.receive(packet);
                byte[] data = packet.getData();
                String msg = new String(data);
                System.out.println (msg);

            } catch (InterruptedIOException e){
                endOfCom = true;
            } catch (IOException io){/* err */ }
        } while (! endOfCom);

    }
}

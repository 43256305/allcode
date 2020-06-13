import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

/**
 * @program: netexperiment3
 * @description:
 * @author: xjh
 * @create: 2020-05-18 17:10
 **/
public class DaytimeServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss=new ServerSocket(2007);
            while(true){
                Socket s=ss.accept();
                new Thread(new ThreadServer(s)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTime(DataOutputStream out) throws IOException{
        Calendar current=Calendar.getInstance();
        out.writeInt(current.get(Calendar.YEAR));
        out.writeByte(current.get(Calendar.MONTH));
        out.writeByte(current.get(Calendar.DAY_OF_MONTH));
        out.writeByte(current.get(Calendar.HOUR_OF_DAY));
        out.writeByte(current.get(Calendar.MINUTE));
        out.writeByte(current.get(Calendar.SECOND));
    }

    static class ThreadServer implements Runnable{
        private Socket s;
        public ThreadServer(Socket s){
            this.s=s;
        }

        @Override
        public void run() {
            DataOutputStream out=null;
            try{
                out=new DataOutputStream(
                        s.getOutputStream());
                writeTime(out);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try{
                    if (out!=null){
                        out.close();
                    }
                    if (s!=null){
                        s.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}

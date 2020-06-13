import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @program: netexperiment2
 * @description:
 * @author: xjh
 * @create: 2020-04-29 16:27
 **/
public class ex3 {
    public static void main(String[] args) {
        new Thread(new Copy()).start();
    }
    static class Copy implements Runnable{
        @Override
        public void run() {
            new Thread(new CopyFile("test.txt","a")).start();
            new Thread(new CopyFile("test.txt","b")).start();
            new Thread(new CopyFile("test.txt","c")).start();
        }
    }
    static class CopyFile implements Runnable{
        private String fileName;
        private String fold;
        public CopyFile(){
        }

        public CopyFile(String fileName,String fold){
            this.fileName=fileName;
            this.fold=fold;
        }
        @Override
        public void run() {
            FileInputStream fis=null;
            FileOutputStream fos=null;
            try{
                fis=new FileInputStream(fileName);
                fos=new FileOutputStream(fold+"/"+fileName);
                int size=fis.available();
                byte[] b=new byte[size];
                fis.read(b);
                fos.write(b);
                fis.close();
                fos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}


package lab.lab2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @program: netexperiment1
 * @description:
 * @author: xjh
 * @create: 2020-04-29 15:15
 **/
public class HexIO2 {
    public static void main(String[] args) {
        try {
            FileOutputStream fos=new FileOutputStream("Test2.txt",true);
            fos.close();

            FileInputStream fis = new FileInputStream( "Test2.txt" );
            FileWriter fw = new FileWriter("TestOut.txt");

            int b,n=0;
            while ((b=fis.read())!=-1){
                fw.write(" "+Integer.toHexString(b));
                if (((++n)%10)==0) fw.write("\n");
            }
            fw.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

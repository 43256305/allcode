package lab.lab2;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @program: netexperiment1
 * @description:
 * @author: xjh
 * @create: 2020-04-29 10:49
 **/
public class HexIO {
    public static void main(String[] args){
        try {
            FileInputStream fis = new FileInputStream("Test.txt");
            int b,n=0;
            while ((b=fis.read())!=-1){
//                System.out.println(b);
                System.out.print(" "+Integer.toHexString(b));
                if (((++n)%10)==0) System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

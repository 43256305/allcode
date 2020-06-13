package lab.lab2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @program: netexperiment1
 * @description:
 * @author: xjh
 * @create: 2020-04-29 15:25
 **/
public class CopyFolder {
    public static void main(String[] args) {
        copy("a","b");
    }

    public static void copy(String folder1,String folder2){
        File file1=new File(folder1);
        File file2=new File(folder2);
        if (file1.isDirectory()&&file2.isDirectory()){
            File[] files1=file1.listFiles();
            File[] files2=file2.listFiles();

            for (int i=0;i<files1.length;i++){
                int flag=0;
                for (int j=0;j<files2.length;j++){
                    if (files1[i].getName().equals(files2[j].getName())){
                        flag=1;
                    }
                }
                if (flag==0){
                    copyFile(files1[i].getName(),folder1,folder2);
                }
            }
        }else{
            throw new RuntimeException("请输入文件夹！");
        }
    }

    public static void copyFile(String filename,String folder1,String folder2){
        FileOutputStream fos=null;
        FileInputStream fis=null;
        try{
            fos=new FileOutputStream(folder2+"/"+filename);
            fis=new FileInputStream(folder1+"/"+filename);
            int size=fis.available();
            byte[] b=new byte[size];
            fis.read(b);
            fos.write(b);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package webadv.s99201105.p02;

/**
 * Hello world!
 *
 */
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.file.Paths;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception{
        Scanner read=new Scanner(Paths.get("D:\\源码\\java\\experiment\\p02\\src\\main\\resources\\test.txt"));
        String rightAccount=read.nextLine();
        String rightPassword=read.nextLine();
        Scanner input=new Scanner(System.in);
        System.out.println("please input your account:");
        String account=input.nextLine();
        System.out.println("please input your password:");
        String password=input.nextLine();
        if (rightAccount.equals(account)&&rightPassword.equals(sha256hex(password))){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }
    public static String sha256hex(String input) {
        return DigestUtils.sha256Hex(input);
    }
}

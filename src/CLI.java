import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Scanner;

public class CLI {
    public static void startCli() {
        Runner runner = new Runner();
        runner.run("ENCRYPT", "C:\\\\Users\\\\Tafner\\\\Desktop\\\\large_text_file.txt", "15");
//            System.out.println("Enter command: ");
//            String command = reader.readLine();
//            System.out.println("Enter absolute filepath: ");
//            String path = reader.readLine();
//            if(command.equals("BRUTE_FORCE")){
//                runner.run(command, path);
//            } else {
//                System.out.println("Enter key value: ");
//                String key = reader.readLine();
//                runner.run(command, path, key);
//            }
    }
}

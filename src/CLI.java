import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Scanner;

public class CLI {
    public static void startCli() {
        Runner runner = new Runner();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Enter command [ENCRYPT], [DECRYPT], [BRUTE_FORCE]: ");
            Commands command = Commands.valueOf(reader.readLine());
            System.out.println("Enter absolute file path: ");
            File file = new File(reader.readLine());
            if(command.equals(Commands.BRUTE_FORCE)){
                runner.run(command, file);
                return;
            }
            System.out.println("Enter key value (key can't be zero): ");
            int key = Integer.parseInt(reader.readLine());
            runner.run(command, file, key);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}

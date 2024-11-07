import Exceptions.IllegalCommandException;
import java.io.File;
import java.util.Arrays;

public class Runner {
    private static int key;
    private static File file;
    private static Commands command;
    private static String textToCipher;

    public Runner(){

    }
    private static void encrypt(){
        textToCipher = FileUI.readFromFile(file);
        CaesarCipher cipher = new CaesarCipher(key);
        String s =cipher.encrypt(textToCipher);
        FileUI.writeToFile(file, s, command);
    }
    private static void decrypt(){
        textToCipher = FileUI.readFromFile(file);
        key *= -1;
        CaesarCipher cipher = new CaesarCipher(key);
        cipher.encrypt(textToCipher);
        FileUI.writeToFile(file, cipher.encrypt(textToCipher), command);
    }
    public void run(String command, String file, String key){
        Runner.command = Commands.valueOf(command);
        Runner.file = new File(file);
        Runner.key = Integer.parseInt(key);

        if(!Runner.file.exists()){
            throw new RuntimeException("file you entered doesn't exist");
        }
        if(Runner.command.equals(Commands.ENCRYPT)){
            encrypt();
        } else if (Runner.command.equals(Commands.DECRYPT)) {
            decrypt();
        } else{
            throw new IllegalCommandException("command you entered doesn't exist, try: [ENCRYPT],[DECRYPT],[BRUTE_FORCE]");
        }
    }
    public void run(String command, String file){
        Runner.file = new File(file);
        Runner.command = Commands.valueOf(command);
        if(!Runner.file.exists()){
            throw new RuntimeException("file you entered doesn't exist");
        }
         if (Runner.command.equals(Commands.BRUTE_FORCE)) {

        } else{
            throw new IllegalCommandException("command you entered doesn't exist, try: [ENCRYPT],[DECRYPT],[BRUTE_FORCE]");
        }
    }

    public void runCli() {
        CLI.startCli();
    }
}

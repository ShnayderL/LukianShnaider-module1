import Exceptions.IllegalCommandException;
import java.io.File;
import java.util.Arrays;

public class Runner {
    public Runner(){

    }
    private static void encrypt(Commands command, File file, int key){
        String textToCipher = FileUI.readFromFile(file);
        CaesarCipher cipher = new CaesarCipher(key);
        FileUI.writeToFile(file, cipher.encrypt(textToCipher), command);
    }
    private static void decrypt(Commands command, File file, int key){
        String textToCipher = FileUI.readFromFile(file);
        CaesarCipher cipher = new CaesarCipher(key);
        FileUI.writeToFile(file, cipher.decrypt(textToCipher), command);
    }
    public void run(Commands command, File file, int key){
        if(!file.exists()){
            throw new RuntimeException("file you entered doesn't exist");
        }
        if(command.equals(Commands.ENCRYPT)){
            encrypt(command, file, key);
        } else if (command.equals(Commands.DECRYPT)) {
            decrypt(command, file, key);
        } else{
            throw new IllegalCommandException("command you entered doesn't exist, try: [ENCRYPT],[DECRYPT],[BRUTE_FORCE]");
        }
    }
    public void run(Commands command, File file){
        if(file.exists()){
            throw new RuntimeException("file you entered doesn't exist");
        }
         if (command.equals(Commands.BRUTE_FORCE)) {

        } else{
            throw new IllegalCommandException("command you entered doesn't exist, try: [ENCRYPT],[DECRYPT],[BRUTE_FORCE]");
        }
    }

    public void runCli() {
        CLI.startCli();
    }

    public void runWithArgs(String[] args) {
        if(args.length == 2){
            if(!Commands.valueOf(args[0]).equals(Commands.BRUTE_FORCE)){
                throw new IllegalCommandException("if you are not using [BRUTE_FORCE], please enter key");
            }
            run(Commands.valueOf(args[0]), new File(args[1]));
        }else if(args.length == 3){
            run(Commands.valueOf(args[0]), new File(args[1]), Integer.parseInt(args[2]));
        }
    }
}

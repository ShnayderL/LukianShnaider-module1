package UI;

import Exceptions.IllegalCommandException;
import Exceptions.IllegalKeyException;

import java.io.File;

public class ValidationUI {
    public static int formatKey(final int key){
        if(key != 0){
            return key;
        }
        return 0;
    }
    public static boolean isValidFile(File file){
        if(file.isFile()){
            return true;
        }
        throw new IllegalArgumentException("File path is incorrect");
    }
    public static boolean isValidCommand(String command){
        if (command.equals("ENCRYPT") || command.equals("DECRYPT") || command.equals("BRUTE_FORCE")) {
            return true;
        }
        throw new IllegalKeyException("You are using wrong command. Use: [ENCRYPT, DECRYPT, BRUTE_FORCE]");
    }
}

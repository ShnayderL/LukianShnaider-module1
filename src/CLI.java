import UI.FileUI;

import java.io.File;

import static UI.FileUI.readFromFile;
import static UI.FileUI.writeToFile;

public class CLI {
    public static void main(String[] args) {
        int key = -15;
        String command = "[DECRYPTED]";
        File file = new File("src/large_text_file[ENCRYPTED].txt");
        String content = readFromFile(file);
        key = CaesarCipher.makeKeyCorrect(key);
        String moved = CaesarCipher.moveAllSymbols(content, key);
        writeToFile(file, moved, command);
    }
}

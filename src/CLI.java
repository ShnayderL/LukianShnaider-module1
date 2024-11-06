import UI.FileUI;

import java.io.File;

import static UI.FileUI.readFromFile;
import static UI.FileUI.writeToFile;

public class CLI {
    public static void main(String[] args) {
        File file = new File("src/large_text_file.txt");
        File file2 = new File("src/text2.txt");
        String content = readFromFile(file);
        writeToFile(file2, content);
    }
}

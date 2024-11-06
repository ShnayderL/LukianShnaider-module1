package UI;
import java.io.*;

public class FileUI {
    public static String readFromFile(File file){
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }
    public static void writeToFile(File file, String content){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

import Exceptions.FileProblemsException;

import java.io.*;

public class FileUI {
    public static String readFromFile(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }

    public static void writeToFile(File file, String content, Commands command, int key) {
        if (file.isAbsolute()) {
            File newFile = newFileName(file, command, key);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
                writer.write(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("file path should be absolute");
        }
    }

    private static File newFileName(File file, Commands command, int key){
        String newFileName = " ";
        String fileName = file.getName();
        if (command.equals(Commands.ENCRYPT)) {
            if (fileName.endsWith("[ENCRYPTED].txt")) {
                throw new FileProblemsException("file was already encrypted, decrypt it first");
            }
            if (fileName.endsWith("[DECRYPTED].txt")) {
                newFileName = file.toString().replace("[DECRYPTED].txt", "[ENCRYPTED].txt");
            } else {
                newFileName = file.toString().replace(".txt", "[ENCRYPTED].txt");
            }
        } else if (command.equals((Commands.DECRYPT))) {
            if (!fileName.endsWith("[ENCRYPTED].txt")) {
                throw new FileProblemsException("file should be previously encrypted to decrypt it");
            }
            newFileName = file.toString().replace("[ENCRYPTED].txt", "[DECRYPTED].txt");
        } else if (command.equals(Commands.BRUTE_FORCE)) {
            newFileName = file.toString().replace(".txt", "(B key-" + key + ").txt");
        }
        return new File(newFileName);
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CaesarCipher {
    public static List<Character> englishLetters = Arrays.asList(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ',
            '!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
            '-', '_', '=', '+', '[', ']', '{', '}', ';', ':',
            '\'', '"', ',', '<', '.', '>', '/', '?', '\\', '|',
            '~', '`'
    );
     public static int makeKeyCorrect(int key){
         if(key > englishLetters.size()){
             return key -(key/englishLetters.size() * englishLetters.size());
         }
         return key;
     }

    public static String moveAllSymbols(String text, int key) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (englishLetters.contains(symbol)) {
                int newSymbolIndex = englishLetters.indexOf(symbol) + key;
                int length = englishLetters.size();

                if (newSymbolIndex < length && newSymbolIndex >= 0) {
                    resultString.append(englishLetters.get(newSymbolIndex));
                } else if(newSymbolIndex >= length){
                    resultString.append(englishLetters.get(newSymbolIndex - length));
                } else {
                    resultString.append(englishLetters.get(length + newSymbolIndex));
                }
            }
        }
        return resultString.toString();
    }
}

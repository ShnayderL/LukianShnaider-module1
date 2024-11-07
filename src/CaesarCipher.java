import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CaesarCipher {
    private int key;
    public CaesarCipher(int key){
        this.key = makeKeyCorrect(key);
    }
    private static final List<Character> englishLetters = Arrays.asList(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    );
    private static final List<Character> ukrainianLetters = Arrays.asList(
            'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж', 'з', 'и', 'і', 'ї', 'й',
            'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч',
            'ш', 'щ', 'ь', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Ґ', 'Д', 'Е', 'Є', 'Ж',
            'З', 'И', 'І', 'Ї', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т',
            'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ю', 'Я'
    );
    private static final List<Character> symbols = Arrays.asList(
            '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '
    );
     private int makeKeyCorrect(int key){
         if(key > englishLetters.size()){
             return key -(key/englishLetters.size() * englishLetters.size());
         }
         if(key < 0){
             key *= (-1);
             key -= (key/englishLetters.size() * englishLetters.size());
             return key *= (-1);
         }
         return key;
     }

    private String moveAllSymbols(String text, int step) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (englishLetters.contains(symbol)) {
                moveSymbol(symbol, step, resultString , englishLetters);
            } else if(ukrainianLetters.contains(symbol)){
                moveSymbol(symbol, step, resultString, ukrainianLetters);
            } else if(symbols.contains(symbol)){
                moveSymbol(symbol, step, resultString, symbols);
            }

        }
        return resultString.toString();
    }
    private void moveSymbol(char c, int step, StringBuilder resultString, List<Character> language){
        int newSymbolIndex = language.indexOf(c) + step;
        int length = language.size();

        if (newSymbolIndex < length && newSymbolIndex >= 0) {
            resultString.append(language.get(newSymbolIndex));
        } else if(newSymbolIndex >= length){
            resultString.append(language.get(newSymbolIndex - length));
        } else {
            resultString.append(language.get(length + newSymbolIndex));
        }
    }
    public String encrypt(String text){
         return moveAllSymbols(text, key);
    }
}

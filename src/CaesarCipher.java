import Exceptions.IllegalKeyException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CaesarCipher {
    private int key;
    public CaesarCipher(int key){
        this.key = makeKeyCorrect(key);
    }
    private static final List<Character> ENGLISH_LETTERS = Arrays.asList(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    );
    private static final List<Character> UKRAINIAN_LETTERS = Arrays.asList(
            'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж', 'з', 'и', 'і', 'ї',
            'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х',
            'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Ґ', 'Д',
            'Е', 'Є', 'Ж', 'З', 'И', 'І', 'Ї', 'Й', 'К', 'Л', 'М', 'Н', 'О',
            'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ю',
            'Я'
    );
    private static final List<Character> SYMBOLS = Arrays.asList(
            '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '
    );
     private int makeKeyCorrect(int key){
         if(key == 0){
             throw new IllegalKeyException("key can not be zero");
         }
         if(key > ENGLISH_LETTERS.size()){
             return key -(key/ENGLISH_LETTERS.size() * ENGLISH_LETTERS.size());
         }
         if(key < 0){
             key *= (-1);
             key -= (key/ENGLISH_LETTERS.size() * ENGLISH_LETTERS.size());
             return key *= (-1);
         }
         return key;
     }

    private String moveAllSymbols(String text, int step) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (ENGLISH_LETTERS.contains(symbol)) {
                moveSymbol(symbol, step, resultString , ENGLISH_LETTERS);
            } else if(UKRAINIAN_LETTERS.contains(symbol)){
                moveSymbol(symbol, step, resultString, UKRAINIAN_LETTERS);
            } else if(SYMBOLS.contains(symbol)){
                moveSymbol(symbol, step, resultString, SYMBOLS);
            }

        }
        return resultString.toString();
    }
    private void moveSymbol(char c, int step, StringBuilder resultString, List<Character> language){
        int length = language.size();
        int newSymbolIndex = (language.indexOf(c) + step) % length;

        if(newSymbolIndex < 0){
            newSymbolIndex += length;
        }
        resultString.append(language.get(newSymbolIndex));
    }
    public String encrypt(String text){
         return moveAllSymbols(text, key);
    }
    public String decrypt(String text){
         return moveAllSymbols(text, -key);
    }
}

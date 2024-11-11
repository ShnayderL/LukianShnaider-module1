import Exceptions.IllegalKeyException;

import java.util.*;

public class CaesarCipher {
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
    private boolean startsWithUpperCase(String text) {
        if (text.isEmpty()) {
            return false; // Порожній рядок не може починатися з великої літери
        }
        char firstChar = text.charAt(0);
        return Character.isUpperCase(firstChar);
    }
    public int countSpaces(String text) {
        int spaceCount = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                spaceCount++;
            }
        }
        return spaceCount;
    }
    public int countDots(String text) {
        int dotsCount = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '.') {
                dotsCount++;
            }
        }
        return dotsCount;
    }
    private int countCommonWords(String text){
        List<String> commonWords = Arrays.asList(
                "і", "І", "в", "В", "на", "На", "з", "З", "що", "Що", "до", "До", "не", "Не",
                "та", "Та", "я", "Я", "ти", "Ти", "він", "Він", "вона", "Вона", "це", "Це",
                "як", "Як", "у", "У", "так", "Так", "але", "Але", "все", "Все", "його", "Його",
                "для", "Для", "про", "Про", "бо", "Бо", "або", "Або", "the", "The", "and", "And",
                "of", "Of", "to", "To", "a", "A", "in", "In", "is", "Is", "it", "It", "you", "You",
                "that", "That", "he", "He", "was", "Was", "for", "For", "on", "On", "are", "Are",
                "with", "With", "as", "As", "I", "His", "they", "They", "be", "Be", "at", "At", ". ", ", "
        );
        int commonWordsCount = 0;
        for (String word : commonWords) {
            if (text.contains(word)) {
                commonWordsCount += 1;
            }
        }
        return commonWordsCount;
    }
    public Map<Integer, Integer> bruteForce(String text){
         int pointsOfProbableVariant = 0;
         int key = 0;
         Map<Integer, Integer> keys = new HashMap<>();
        for (int i = 1; i <= UKRAINIAN_LETTERS.size(); i++) {
            String probableVariant = decrypt(text, i);
            if(startsWithUpperCase(text)){
                pointsOfProbableVariant +=1;
            }
            pointsOfProbableVariant += countSpaces(probableVariant);
            pointsOfProbableVariant += countDots(probableVariant);
            pointsOfProbableVariant += countCommonWords(probableVariant);

            if(pointsOfProbableVariant > 1){
                if(pointsOfProbableVariant >= key){
                    key = pointsOfProbableVariant;
                    keys.put(pointsOfProbableVariant, i);
                }
            }
            pointsOfProbableVariant = 0;
        }
         return keys;
    }
    private void moveSymbol(char c, int step, StringBuilder resultString, List<Character> language){
        int length = language.size();
        int newSymbolIndex = (language.indexOf(c) + step) % length;

        if(newSymbolIndex < 0){
            newSymbolIndex += length;
        }
        resultString.append(language.get(newSymbolIndex));
    }
    public String encrypt(String text, int key){
        makeKeyCorrect(key);
         return moveAllSymbols(text, key);
    }
    public String decrypt(String text, int key){
        makeKeyCorrect(key);
        return moveAllSymbols(text, -key);
    }
}

package Task1;

import java.util.Map;
import java.util.TreeMap;

public class MainApp {
    public static void main(String[] args) {
        String[] repeatedWords = new String[20];
        for (int i = 0; i < repeatedWords.length; i++) {
            repeatedWords[i] = "word" + i%7;
        }

        for(String s : repeatedWords){
            System.out.println(s);
        }

        Map<String, Integer> uniqueWords = new TreeMap<>();
        for(String s : repeatedWords){
            if(!uniqueWords.containsKey(s)){
                uniqueWords.put(s, 1);
            }
            else {
                uniqueWords.replace(s, uniqueWords.get(s)+1);
            }
        }

        for (Map.Entry<String, Integer> o : uniqueWords.entrySet()){
            System.out.println(o.getKey() + ": " + o.getValue());
        }
    }
}

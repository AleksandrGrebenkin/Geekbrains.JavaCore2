package Task2;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class PhoneBook {
    private Map<String, TreeSet<String>> phoneBook = new TreeMap<>();

    public void add(String name, String phoneNumber){
        if(!phoneBook.containsKey(name)){
            TreeSet<String> phones = new TreeSet<>();
            phones.add(phoneNumber);
            phoneBook.put(name, phones);
        }
        else {
            TreeSet<String> phones = phoneBook.get(name);
            phones.add(phoneNumber);
            phoneBook.replace(name, phones);
        }
    }

    public void get(String name){
        for(String s : phoneBook.get(name)){
            System.out.println(s);
        }
    }
}

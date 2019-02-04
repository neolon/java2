package java2.lesson3.homework;

import java.util.HashMap;
import java.util.Map;


public class Second {

    private static HashMap<Integer, String> phoneBook = new HashMap<Integer, String>();



    public static void main(String[] args) {
        add(1111111, "Иванов");
        add(2222222, "Смирнов");
        add(3333333, "Петров");
        add(4444444, "Егоров");
        add(5555555, "Смирнов");
        get("Смирнов");
    }

    private static void add(Integer number, String name) {
        phoneBook.put(number, name);
    }

    private static void get(String name) {
        for (Map.Entry<Integer, String> entry : phoneBook.entrySet()) {
            if (entry.getValue().equals(name)) System.out.println(entry.getKey());
        }
    }


}



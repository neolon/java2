package java2.lesson3.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class First {

    public static void main(String[] args) {


//    1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
//    Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
//    Посчитать сколько раз встречается каждое слово.

        ArrayList<String> arr1 = new ArrayList();
        arr1.add("Audi");
        arr1.add("Mercedes");
        arr1.add("Volkswagen");
        arr1.add("Audi");
        arr1.add("Renault");
        arr1.add("Ford");
        arr1.add("BMW");
        arr1.add("Ford");
        arr1.add("Toyota");
        arr1.add("Mercedes");
        uniqueNamesCounter(arr1);
    }

    private static void uniqueNamesCounter(ArrayList<String> arr1) {
        int count = 0;
        for (int i = 0; i < arr1.size(); i++) {
            String mark = arr1.get(i);
            for (int j = 0; j < arr1.size(); j++) {
                if (mark.equals(arr1.get(j))) {
                    count++;
                    if (count == 2) arr1.remove(j);
                }
            }
            System.out.println(mark + " " + count);
            count = 0;
        }
    }
}





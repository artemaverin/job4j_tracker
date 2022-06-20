package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("artem-averin-91@mail.ru", "Artem Averin");
        map.put("pushkinas@gmail.com", "Pushkin Alexandr");
        map.put("artem-averin-91@mail.ru", "Averin Artem Alekseevich");
        map.put("gogolnv@bk.ru", "Gogol Nikolai");
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println("key: " + key + " - " + "value: " + value);
        }
    }
}

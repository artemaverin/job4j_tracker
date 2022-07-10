package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] o1s = o1.split("/");
        String[] o2s = o2.split("/");
        int rsl = o2s[0].compareTo(o1s[0]);
        return rsl == 0 ? o1.compareTo(o2) : rsl;
    }
}

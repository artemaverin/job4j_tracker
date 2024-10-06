package ru.job4j.list;

import java.util.List;
import java.util.ListIterator;

public class ArProgression {
    public static void main(String[] args) {

        List<Integer> data = List.of(
                1, 6, 11, 16, 21,
                26, 31, 36, 41, 46
        );
        ListIterator<Integer> iterator = data.listIterator(2);
        ListIterator<Integer> iterator2 = data.listIterator(3);
        System.out.println(checkData(data));
    }

    public static int checkData(List<Integer> data) {
        int res = 0;
        int j = 0;
        int i = 1;
            ListIterator<Integer> iterator = data.listIterator(1);
            ListIterator<Integer> iterator2 = data.listIterator(2);
            while (iterator.hasPrevious()) {
                if (((double) (iterator.previous() + iterator2.next()) / 2 != data.get(i))) {
                    return 0;
                } else {
                    res += data.get(j);
                    j++;
                    i++;
                }
            }
        return res;
    }
}

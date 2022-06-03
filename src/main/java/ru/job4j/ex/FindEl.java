package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        if (key == null) {
            throw new ElementNotFoundException("Аргумент key содержит null");
        }
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (key.equals(value[i])) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        try {
            System.out.println(FindEl.indexOf(new String[] {"one", "two"}, null));
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll() {
        Item[] itemCopy = new Item[items.length];
        int size = 0;
        for (int i = 0; i < items.length; i++) {
            Item element = items[i];
            if (element != null) {
                itemCopy[size] = element;
                size++;
            }
        }
        return Arrays.copyOf(itemCopy, size);
    }

    public Item[] findByName(String key) {
        Item[] itemCopy = new Item[items.length];
        int size = 0;
        for (int i = 0; i < items.length; i++) {
            Item element = items[i];
            if (element != null && element.getName().equals(key)) {
                itemCopy[size] = element;
                size++;
            }
        }
        return Arrays.copyOf(itemCopy, size);
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        item.setId(id);
        items[index] = item;
        return item.getId() == id;
    }
}
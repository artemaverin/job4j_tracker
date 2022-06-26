package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ItemDescByNameTest {

    @Test
    public void whenCompareDesc() {
        List<Item> items = Arrays.asList(
                new Item("one"),
                new Item("two"),
                new Item("four"));
        List<Item> expected = Arrays.asList(
                new Item("two"),
                new Item("one"),
                new Item("four"));
        items.sort(new ItemDescByName());
        assertThat(items, is(expected));
    }
}
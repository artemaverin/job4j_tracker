package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class MemTrackerTest {
    @Test
    public void whenTestFindById() {
        Store store = new MemTracker();
        Item bug = new Item("Bug");
        Item item = store.add(bug);
        Item result = store.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenTestFindAll() {
        Store store = new MemTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        store.add(first);
        store.add(second);
        Item result = store.findAll().get(0);
        assertThat(result.getName(), is(first.getName()));
    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        Store store = new MemTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        store.add(first);
        store.add(second);
        store.add(new Item("First"));
        store.add(new Item("Second"));
        store.add(new Item("First"));
        List<Item> result = store.findByName(first.getName());
        assertThat(result.size(), is(3));
    }

    @Test
    public void whenTestFindByNameCheckSecondItemName() {
        Store store = new MemTracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        store.add(first);
        store.add(second);
        store.add(new Item("First"));
        store.add(new Item("Second"));
        store.add(new Item("First"));
        List<Item> result = store.findByName(second.getName());
        assertThat(result.get(0).getName(), is(second.getName()));
    }

    @Test
    public void whenReplace() {
        Store store = new MemTracker();
        Item bug = new Item();
        bug.setName("Bug");
        store.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        store.replace(id, bugWithDesc);
        assertThat(store.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        Store store = new MemTracker();
        Item bug = new Item();
        bug.setName("Bug");
        store.add(bug);
        int id = bug.getId();
        store.delete(id);
        assertThat(store.findById(id), is(nullValue()));
    }
}
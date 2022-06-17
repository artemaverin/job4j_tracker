package ru.job4j.search;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.util.ArrayList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        ArrayList<Person> persons = phones.find("Petr");
        assertThat(persons.get(0).getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindByIsEmpty() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        ArrayList<Person> persons = phones.find("Dima");
        assertThat(null, Matchers.is(nullValue()));
    }
}
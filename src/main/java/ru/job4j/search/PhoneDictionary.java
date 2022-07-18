package ru.job4j.search;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> predPhone = p -> p.getPhone().contains(key);
        Predicate<Person> predName = p -> p.getName().contains(key);
        Predicate<Person> predAddress = p -> p.getAddress().contains(key);
        Predicate<Person> predSurname = p -> p.getSurname().contains(key);
        Predicate<Person> combine = predPhone.or(predName).or(predAddress).or(predSurname);
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }

}

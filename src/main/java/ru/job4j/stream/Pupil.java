package ru.job4j.stream;

import java.util.List;

public record Pupil(String name, List<Subject> subjects) {

    public String getName() {
        return name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}

package ru.job4j.collection;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.notification.RunListener;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class JobTest {
    @Test
    public void whenCompatorByNameAndPrority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByNameandPriorityAsc() {
        Comparator<Job> cmpNamePriority = new JobAscByPriority().thenComparing(new JobAscByName());
        int rsl = cmpNamePriority.compare(
                new Job("program error", 3),
                new Job("refactoring", 2)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByNameAsc() {
        List<Job> jobs = Arrays.asList(
                new Job("refactoring", 2),
                new Job("Fix bug", 1)
        );
        int rsl = new Job("Fix bug", 1).compareTo(new Job("refactoring", 2));
        jobs.sort(new JobAscByName());
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByNameDesc() {
        List<Job> jobs = Arrays.asList(
                new Job("Impl task", 0),
                new Job("program error", 3)
        );
        int rsl = new Job("program error", 3).compareTo(new Job("Impl task", 0));
        jobs.sort(new JobDescByName());
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByPriorityAsc() {
        List<Job> jobs = Arrays.asList(
                new Job("refactoring", 2),
                new Job("Fix bug", 1)
        );
        int rsl = new Job("refactoring", 2).compareTo(new Job("Fix bug", 1));
        jobs.sort(new JobAscByPriority());
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByPriorityDesc() {
        List<Job> jobs = Arrays.asList(
                new Job("Impl task", 3),
                new Job("program error", 3)
        );
        int rsl = new Job("program error", 3).compareTo(new Job("Impl task", 3));
        jobs.sort(new JobDescByPriority());
        assertThat(rsl, is(0));
    }

}

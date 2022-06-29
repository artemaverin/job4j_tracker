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
    public void whenComparatorByNameAndPriorityAsc() {
        Comparator<Job> cmpPriorityName = new JobAscByPriority().thenComparing(new JobAscByName());
        int rsl = cmpPriorityName.compare(
                new Job("program error", 3),
                new Job("refactoring", 2)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByNameAsc() {
        Comparator<Job> cmpNameAsc = new JobAscByName();
        int rsl = cmpNameAsc.compare(
                new Job("Fix bug", 1),
                new Job("refactoring", 2));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByNameDesc() {
        Comparator<Job> cmpNameDesc = new JobDescByName();
        int rsl = cmpNameDesc.compare(
                new Job("program error", 3),
                new Job("Impl task", 0));
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByPriorityAsc() {
        Comparator<Job> cmpPriorityAsc = new JobAscByPriority();
        int rsl = cmpPriorityAsc.compare(
                new Job("refactoring", 2),
                new Job("Fix bug", 1));
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByPriorityDesc() {
        Comparator<Job> cmpPriorityDesc = new JobDescByPriority();
        int rsl = cmpPriorityDesc.compare(
                new Job("program error", 3),
                new Job("Impl task", 3));
        assertThat(rsl, is(0));
    }
}

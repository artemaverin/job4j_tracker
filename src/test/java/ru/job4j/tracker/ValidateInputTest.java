package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {
    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("1");
        Input in = new StubInput(list);
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenValidInput() {
        Output out = new StubOutput();
        List<String> list = new ArrayList<>();
        list.add("1");
        Input in = new StubInput(list);
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenMultipleValidInput() {
       Output out = new StubOutput();
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("2");
        list.add("1");
        Input in = new StubInput(list);
       ValidateInput input = new ValidateInput(out, in);
       int selected = input.askInt("Enter menu:");
       assertThat(selected, is(0));
       int selected2 = input.askInt("Enter menu:");
       assertThat(selected2, is(2));
       int selected3 = input.askInt("Enter menu:");
       assertThat(selected3, is(1));
    }

    @Test
    public void whenNegativeInput() {
        Output out = new StubOutput();
        List<String> list = new ArrayList<>();
        list.add("-2");
        Input in = new StubInput(list);
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(-2));
    }
}
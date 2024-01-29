package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByNameActionTest {

    @Test
    public void whenItemFindByNameSuccessfully() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        String name1 = "Item1";
        String name3 = "Item3";
        Item item1 = new Item(name1);
        Item item3 = new Item(name3);
        tracker.add(item1);
        tracker.add(item3);
        FindByNameAction findByNameAction = new FindByNameAction(output);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(name3);

        findByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + item3 + ln
        );
    }

    @Test
    public void whenItemWasNotFoundByName() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        String name1 = "Item1";
        String name3 = "Item3";
        String notFoundId = "Item5";
        Item item1 = new Item(name1);
        Item item3 = new Item(name3);
        tracker.add(item1);
        tracker.add(item3);
        FindByNameAction findByNameAction = new FindByNameAction(output);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(notFoundId);

        findByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();

        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + "Заявки с именем: " + notFoundId + " не найдены." + ln
        );
    }
}
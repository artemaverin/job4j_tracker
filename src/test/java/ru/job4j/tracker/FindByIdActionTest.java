package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByIdActionTest {

    @Test
    public void whenItemFindByIdSuccessfully() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item item1 = new Item(1, "Item1");
        Item item3 = new Item(3, "Item3");
        tracker.add(item1);
        tracker.add(item3);
        FindByIdAction findByIdAction = new FindByIdAction(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        findByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + item1 + ln
        );
    }

    @Test
    public void whenItemWasNotFoundById() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        int id1 = 1;
        int id3 = 3;
        int notFoundId = 3;
        Item item1 = new Item(id1, "Item1");
        Item item3 = new Item(id3, "Item3");
        tracker.add(item1);
        tracker.add(item3);
        FindByIdAction findByIdAction = new FindByIdAction(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(notFoundId);

        findByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();

        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + "Заявка с введенным id: " + notFoundId + " не найдена." + ln
        );
    }

}
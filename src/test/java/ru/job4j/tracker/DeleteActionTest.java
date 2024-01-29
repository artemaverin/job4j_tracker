package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteActionTest {

    @Test
    public void whenItemWasDeletedSuccessfully() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Delete item"));
        DeleteAction deleteAction = new DeleteAction(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(tracker.findAll()).isEmpty();
        assertThat(output.toString()).isEqualTo(
                "=== Delete item ===" + ln
                        + "Заявка удалена успешно." + ln
        );
    }

    @Test
    public void whenItemWasNotDeleted() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Delete item"));
        DeleteAction deleteAction = new DeleteAction(output);

        Input input = mock(Input.class);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(tracker.findAll()).hasSize(1);
        assertThat(output.toString()).isEqualTo(
                "=== Delete item ===" + ln
                        + "Ошибка удаления заявки." + ln
        );
    }
}
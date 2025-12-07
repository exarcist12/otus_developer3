package ru.otus.handler;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.listener.Listener;
import ru.otus.model.Message;
import ru.otus.processor.*;

class ComplexProcessorTest {

    @Test
    @DisplayName("Тестируем вызовы процессоров")
    void handleProcessorsTest() {
        // given
        var message = new Message.Builder(1L).field7("field7").build();

        var processor1 = mock(Processor.class);
        when(processor1.process(message)).thenReturn(message);

        var processor2 = mock(Processor.class);
        when(processor2.process(message)).thenReturn(message);

        var processors = List.of(processor1, processor2);

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {});

        // when
        var result = complexProcessor.handle(message);

        // then
        verify(processor1).process(message);
        verify(processor2).process(message);
        assertThat(result).isEqualTo(message);
    }

    @Test
    @DisplayName("Тестируем вызовы процессоров")
    void handleProcessorsTest2() {
        // given
        var message =
                new Message.Builder(1L).field11("field11").field11("field12").build();

        var processor1 = new ProcessorValueChangesFields11With12();

        List<Processor> processors = List.of(processor1);

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {});

        // when
        var result = complexProcessor.handle(message);

        // then
        assertThat(result)
                .isEqualTo(new Message.Builder(1L)
                        .field11("field12")
                        .field11("field11")
                        .build());
    }

    @Test
    @DisplayName("Тестируем обработку исключения при четной секунде")
    void handleExceptionTest() {
        // given
        var message = new Message.Builder(1L).field8("field8").build();

        DateTimeProvider evenSecondProvider = () -> LocalDateTime.of(2023, 1, 1, 12, 0, 2);
        var processor1 = new ProcessorThrowOnEvenSecond(evenSecondProvider);

        List<Processor> processors = List.of(processor1);

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
            throw new ProcessorThrowOnEvenSecond.NewException(ex.getMessage());
        });

        // when
        assertThatExceptionOfType(ProcessorThrowOnEvenSecond.NewException.class)
                .isThrownBy(() -> complexProcessor.handle(message));
    }

    @Test
    @DisplayName("Тестируем обработку исключения при четной секунде")
    void handleExceptionTest3() {
        // given
        var message = new Message.Builder(1L).field8("field8").build();

        DateTimeProvider systemProvider = new SystemDateTimeProvider();
        var processor1 = new ProcessorThrowOnEvenSecond(systemProvider);

        List<Processor> processors = List.of(processor1);

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
            throw new ProcessorThrowOnEvenSecond.NewException(ex.getMessage());
        });

        // when
        assertThatExceptionOfType(ProcessorThrowOnEvenSecond.NewException.class)
                .isThrownBy(() -> complexProcessor.handle(message));
    }

    @Test
    @DisplayName("Тестируем обработку исключения при нечетной секунде")
    void handleExceptionTest2() {
        // given
        var message = new Message.Builder(1L).field8("field8").build();

        DateTimeProvider oddSecondProvider = () -> LocalDateTime.of(2023, 1, 1, 12, 0, 3);
        var processor1 = new ProcessorThrowOnEvenSecond(oddSecondProvider);

        List<Processor> processors = List.of(processor1);

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
            throw new ProcessorThrowOnEvenSecond.NewException(ex.getMessage());
        });

        // when
        assertThatCode(() -> complexProcessor.handle(message)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Тестируем уведомления")
    void notifyTest() {
        // given
        var message = new Message.Builder(1L).field9("field9").build();

        var listener = mock(Listener.class);

        var complexProcessor = new ComplexProcessor(new ArrayList<>(), (ex) -> {});

        complexProcessor.addListener(listener);

        // when
        complexProcessor.handle(message);
        complexProcessor.removeListener(listener);
        complexProcessor.handle(message);

        // then
        verify(listener, times(1)).onUpdated(message);
    }

    private static class TestException extends RuntimeException {
        public TestException(String message) {
            super(message);
        }
    }
}

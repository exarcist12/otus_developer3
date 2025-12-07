package ru.otus.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.model.Message;

public class ProcessorThrowOnEvenSecond implements Processor {
    private static final Logger logger = LoggerFactory.getLogger(ProcessorThrowOnEvenSecond.class);
    private final DateTimeProvider dateTimeProvider;

    public ProcessorThrowOnEvenSecond(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        int second = dateTimeProvider.getDateTime().getSecond();
        logger.info("new second:{}", second);
        if (second % 2 == 0) {
            throw new NewException("Exception on even second: " + second);
        }
        return message;
    }

    public static class NewException extends RuntimeException {
        public NewException(String message) {
            super(message);
        }
    }
}

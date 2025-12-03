package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorValueChangesFields11With12 implements Processor {

    @Override
    public Message process(Message message) {

        message = message.toBuilder()
                .field11(message.getField12())
                .field12(message.getField11())
                .build();
        return message;
    }
}

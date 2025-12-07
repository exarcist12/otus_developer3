package ru.otus.listener.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import ru.otus.listener.Listener;
import ru.otus.model.Message;

public class HistoryListener implements Listener, HistoryReader {

    private final Map<Long, Message> history = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        Message messageCopy = new Message.Builder(msg).build();
        history.put(msg.getId(), messageCopy);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        Message found = history.get(id);
        return found == null ? Optional.empty() : Optional.of(found);
    }
}

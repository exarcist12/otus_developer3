package ru.otus.processor;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemDateTimeProvider implements DateTimeProvider {
    private static final Logger logger = LoggerFactory.getLogger(SystemDateTimeProvider.class);

    @Override
    public LocalDateTime getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        logger.info("time: {}", now);
        return now;
    }
}

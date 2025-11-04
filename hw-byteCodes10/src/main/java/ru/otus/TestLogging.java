package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogging implements TestLoggingInterface {
    private static final Logger logger = LoggerFactory.getLogger(TestLogging.class);

    @Log
    @Override
    public void calculation(int param) {
        logger.info("calculation, param:{}", param);
    }

    @Override
    public void calculation(int param, int param2) {
        logger.info("calculation, param:{} {}", param, param2);
    }

    @Log
    @Override
    public void calculation(int param, int param2, int param3) {
        logger.info("calculation, param:{} {} {}", param, param2, param3);
    }

    @Log
    @Override
    public void calculation2(int param) {
        logger.info("calculation2, param:{}", param);
    }

    @Override
    public String toString() {
        return "MyClassImpl{}";
    }
}

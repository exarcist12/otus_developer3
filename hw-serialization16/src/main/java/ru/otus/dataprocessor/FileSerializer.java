package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSerializer implements Serializer {
    private static final Logger logger = LoggerFactory.getLogger(FileSerializer.class);
    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) throws IOException {
        // формирует результирующий json и сохраняет его в файл
        ObjectMapper mapper = new ObjectMapper();
        var file = new File(fileName);
        mapper.writeValue(file, data);
        logger.info("data saved to the file:{}", file.getAbsolutePath());
    }
}

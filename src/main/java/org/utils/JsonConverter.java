package org.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonConverter {

    private static final ObjectMapper om = new ObjectMapper();

    static {
        om.registerModule(new JavaTimeModule());
    }

    public static void saveList(List<?> list, File file, Class<?> cl) {
        if (list.isEmpty()) {
            log.debug("List is empty");
            return;
        }

        try {
            om.writerFor(om.getTypeFactory().constructCollectionLikeType(List.class, cl))
                    .writeValue(file, list);
        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }

    public static List<?> getList(File file, Class<?> cl) {
        if (file.length() == 0) return new ArrayList<>();
        try {
            return om.readValue(file, om.getTypeFactory().constructCollectionLikeType(List.class, cl));
        } catch (IOException e) {
            log.debug(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void writeMap(File file, Map map) {
        try {
            om.writeValue(file, map.values());
        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }
}
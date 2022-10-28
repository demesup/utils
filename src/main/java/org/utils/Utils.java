package org.utils;

import lombok.extern.slf4j.Slf4j;
import org.utils.model.Model;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static org.utils.Read.readNumber;

@Slf4j
public class Utils {
    public static String mapInSeparatesLines(Map<?, ?> map) {
        StringBuilder builder = new StringBuilder();
        map.forEach((key, value) -> builder.append(key).append(" : ").append(value).append('\n'));
        return String.valueOf(builder);
    }

    public static String mapInSeparatesLines(String title, Map<?, ?> map, int symbolsOnTheSides) {
        return beautifulTitle(title, symbolsOnTheSides) + mapInSeparatesLines(map);
    }

    public static String listInSeparatedLines(List<?> list) {
        if (list.isEmpty()) return "List of is empty";

        StringBuilder builder = new StringBuilder();

        IntStream.range(0, list.size()).mapToObj(i -> "\n" + i + ": " + list.get(i)).forEach(builder::append);
        return String.valueOf(builder);
    }

    public static String listInSeparatedLines(List<?> list, String message) {
        if (list.isEmpty()) return "List of is empty";

        StringBuilder builder = new StringBuilder();
        builder.append(beautifulTitle(message));
        IntStream.range(0, list.size()).mapToObj(i -> "\n" + i + ": " + list.get(i)).forEach(builder::append);
        return String.valueOf(builder);
    }

    public static String setInSeparatedLines(Set<?> set) {
        StringBuilder builder = new StringBuilder();
        set.forEach(item -> builder.append(item).append("\n"));
        return String.valueOf(builder);
    }

    public static String listWithTitle(List<?> list) {
        try {
            if (list.isEmpty()) return "Empty list";

            StringBuilder builder = new StringBuilder();

            builder.append(beautifulTitle(getTypeOfObject(list)));
            return String.valueOf(builder.append(listInSeparatedLines(list)));
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return null;
    }

    private static String beautifulTitle(String title) {
        return "******************** " + title + " ********************\n";
    }

    private static String beautifulTitle(String title, int number) {
        return symbols(number, '*') +
                ' ' + title + ' ' +
                symbols(number, '*') +
                '\n';
    }

    public static String symbols(int number, char symbol) {
        return String.valueOf(symbol).repeat(Math.max(0, number));
    }

    private static String getTypeOfObject(List<?> list) {
        Class<?> modelClass = list.get(0).getClass();
        if (modelClass.getSuperclass() != Object.class) {
            return modelClass.getSuperclass().getSimpleName();
        } else {
            return modelClass.getSimpleName();
        }
    }

    public static Model findModel(List<? extends Model> list, int number) throws IOException {
        if (list.isEmpty()) throw new RuntimeException("Empty list");
        try {
            return list.get(number);
        } catch (IndexOutOfBoundsException e) {
            log.debug(e.getMessage() + ". Try again");
            return findModel(list, readNumber());
        }
    }

    public static boolean fieldIsAnnotatedBy(Class<? extends Annotation> annotation, Field field) {
        return field.isAnnotationPresent(annotation);
    }

    public static <T> String numberedArray(T[] array){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            builder.append(i).append(" ").append(array[i]).append("\n");
        }
        return String.valueOf(builder);
    }
}

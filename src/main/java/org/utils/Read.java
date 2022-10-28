package org.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import static org.utils.Utils.numberedArray;

@Slf4j
public class Read {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readWordWithCapitalLetter() throws IOException {
        StringBuilder input = new StringBuilder(readStringLowerCase());
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String readWordWithCapitalLetter(String messageBefore) throws IOException {
        log.debug(messageBefore);
        return readWordWithCapitalLetter();
    }

    public static String readStringLowerCase() throws IOException {
        return read().toLowerCase().replaceAll(" ", "");
    }

    public static String readStringLowerCase(String messageBefore) throws IOException {
        log.debug(messageBefore);
        return readStringLowerCase();
    }

    public static String readStringUpperCaseWithoutSpace() throws IOException {
        return readStringWithoutSpaces().toUpperCase(Locale.ROOT);
    }

    public static String readStringUpperCaseWithoutSpace(String messageBefore) throws IOException {
        log.debug(messageBefore);
        return readStringWithoutSpaces().toUpperCase(Locale.ROOT);
    }

    public static String readString(String message) throws IOException {
        log.debug(message);
        return read();
    }

    public static String read() throws IOException {
        return reader.readLine();
    }

    public static String read(String message) throws IOException {
        log.debug(message);
        return reader.readLine();
    }

    public static boolean inputEqualsYes(String message) throws IOException {
        log.debug(message + "press yes/any key");
        return read().equalsIgnoreCase("yes");
    }

    public static boolean inputEqualsYes() throws IOException {
        return read().equalsIgnoreCase("yes");
    }

    public static int readPositiveNumber() throws IOException {
        try {
            int number = Integer.parseInt(read());
            if (number > -1) return number;
        } catch (RuntimeException e) {
            log.debug(e.getMessage() + ". Try again. ");
        }
        return readPositiveNumber();
    }

    public static int readPositiveNumber(String message) throws IOException {
        log.debug(message);
        return readPositiveNumber();
    }

    public static int readNumber() throws IOException {
        log.debug("Enter number");
        try {
            return Integer.parseInt(read());
        } catch (IllegalArgumentException e) {
            log.debug(e.getMessage());
            return readNumber();
        }
    }

    public static int readNumber(String message) throws IOException {
        log.debug(message);
        return readNumber();
    }

    public static int readNumber(int to, String message) throws IOException {
        log.debug(message);
        return readNumber(0, to);
    }

    public static int readNumber(int to) throws IOException {
        return readNumber(0, to);
    }

    public static int readNumber(int from, int to) throws IOException {
        if (from >= to) throw new IllegalArgumentException("From expected to be less than to");
        int i = readNumber();
        if (i >= from && i < to) {
            return i;
        } else {
            log.debug("Number is not in boundaries" + from + ";" + to + ". Try again.");
            return readNumber(from, to);
        }
    }

    public static int readNumber(int from, int to, String message) throws IOException {
        log.debug(message);
        return readNumber(from, to);
    }

    public static String readStringWithoutSpaces() throws IOException {
        return read().replaceAll(" ", "");
    }

    public static String readStringWithoutSpaces(String message) throws IOException {
        log.debug(message);
        return read().replaceAll(" ", "");
    }

    public static char readCharacter(String message) throws IOException {
        log.debug(message);
        return readCharacter();
    }

    private static char readCharacter() throws IOException {
        try {
            var s = read();
            if (s.length() > 1) throw new StringIndexOutOfBoundsException("Entered value is not a character");
            return s.charAt(0);
        } catch (StringIndexOutOfBoundsException e) {
            log.debug(e.getMessage() + ". Try again");
            return readCharacter();
        }
    }

    public static <T> T readEnumValue(T[] enumValues, String message) throws IOException {
        log.debug(message);
        return readEnumValue(enumValues);
    }

    public static <T> T readEnumValue(T[] enumValues) throws IOException {
        log.debug(numberedArray(enumValues));
        return enumValues[readNumber(enumValues.length)];
    }
}

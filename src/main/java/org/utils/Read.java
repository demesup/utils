package org.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import static org.utils.Utils.numberedArray;

public class Read {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readWordWithCapitalLetter() throws IOException {
        StringBuilder input = new StringBuilder(readStringLowerCase());
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String readWordWithCapitalLetter(String messageBefore) throws IOException {
        System.out.println(messageBefore);
        return readWordWithCapitalLetter();
    }

    public static String readStringLowerCase() throws IOException {
        return read().toLowerCase().replaceAll(" ", "");
    }

    public static String readStringLowerCase(String messageBefore) throws IOException {
        System.out.println(messageBefore);
        return readStringLowerCase();
    }

    public static String readStringUpperCaseWithoutSpace() throws IOException {
        return readStringWithoutSpaces().toUpperCase(Locale.ROOT);
    }

    public static String readStringUpperCaseWithoutSpace(String messageBefore) throws IOException {
        System.out.println(messageBefore);
        return readStringWithoutSpaces().toUpperCase(Locale.ROOT);
    }

    public static String read() throws IOException {
        return reader.readLine();
    }

    public static String read(String message) throws IOException {
        System.out.println(message);
        return reader.readLine();
    }

    public static boolean inputEqualsYes(String message) throws IOException {
        System.out.println(message + "press yes/any key");
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
            System.out.println(e.getMessage() + ". Try again. ");
        }
        return readPositiveNumber();
    }

    public static int readPositiveNumber(String message) throws IOException {
        System.out.println(message);
        return readPositiveNumber();
    }

    public static int readNumber() throws IOException {
        System.out.println("Enter number");
        try {
            return Integer.parseInt(read());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readNumber();
        }
    }

    public static int readNumber(String message) throws IOException {
        System.out.println(message);
        return readNumber();
    }

    public static int readNumber(int to, String message) throws IOException {
        System.out.println(message);
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
            System.out.println("Number is not in boundaries" + from + ";" + to + ". Try again.");
            return readNumber(from, to);
        }
    }

    public static int readNumber(int from, int to, String message) throws IOException {
        System.out.println(message);
        return readNumber(from, to);
    }

    public static long readLong() throws IOException {
        System.out.println("Enter number");
        try {
            return Long.parseLong(read());
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            return readNumber();
        }
    }

    public static long readLong(String message) throws IOException {
        System.out.print(message+" ");
        return readLong();
    }

    public static String readStringWithoutSpaces() throws IOException {
        return read().replaceAll(" ", "");
    }

    public static String readStringWithoutSpaces(String message) throws IOException {
        System.out.println(message);
        return read().replaceAll(" ", "");
    }

    public static char readCharacter(String message) throws IOException {
        System.out.println(message);
        return readCharacter();
    }

    private static char readCharacter() throws IOException {
        try {
            var s = read();
            if (s.length() > 1) throw new StringIndexOutOfBoundsException("Entered value is not a character");
            return s.charAt(0);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e.getMessage() + ". Try again");
            return readCharacter();
        }
    }

    public static <T> T readEnumValue(T[] enumValues, String message) throws IOException {
        System.out.println(message);
        return readEnumValue(enumValues);
    }

    public static <T> T readEnumValue(T[] enumValues) throws IOException {
        System.out.println(numberedArray(enumValues));
        return enumValues[readNumber(enumValues.length)];
    }
}

package org.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

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

    public static String readString(String message) throws IOException {
        System.out.println(message);
        return read();
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
            System.out.print(e.getMessage() + ". Try again. ");
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
        return read().charAt(0);
    }
}

package org.utils;

import com.github.curiousoddman.rgxgen.RgxGen;
import org.utils.exception.PatternMismatchException;

import java.io.IOException;
import java.util.regex.Pattern;

import static org.utils.Read.read;

public class Patterns {

    public static boolean checkMatchToPattern(Pattern pattern, String str) {
        if (pattern.equals(phonePattern())) {
            str = str.replaceAll(" ", "");
        }
        return pattern.matcher(str).matches();
    }

    public static void checkMatching(Pattern pattern, String str) {
        if (pattern.equals(phonePattern())) {
            str = str.replaceAll(" ", "");
        }
        if (!pattern.matcher(str).matches()) throw new PatternMismatchException(pattern, str);
    }

    public static String askStringWhileDoesNotMatchToPattern(Pattern pattern, String message) throws IOException {
        while (true) {
            System.out.println(message);
            String str = read();
            if (pattern.equals(phonePattern())) {
                str = str.replaceAll(" ", "");
            }
            if (pattern.matcher(str).matches()) {
                return str;
            } else System.out.println("Does not match to pattern. Try again");
        }
    }

    public static Pattern emailPattern() {
        return Pattern.compile(
                "^[\\w-.]{1,15}@([a-z-]{1,5}\\.){1,3}[a-z-]{2,4}$"
        );
    }

    public static Pattern phonePattern() {
        return Pattern.compile(
                "^(\\+\\d{1,2}\\s?)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{2}[\\s.-]?\\d{2}$"
        );
    }

    public static Pattern simplePhonePattern() {
        return Pattern.compile(
                "^(\\+\\d{1,2})\\(\\d{3}\\)-\\d{3}-\\d{2}-\\d{2}$"
        );
    }

    public static Pattern passwordPattern1char1Capital1number8() {
        return Pattern.compile(
                "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_<>?,./+=-])[A-Za-z\\d!@#$%^&*()_+=,./<>?-]{8,}$"
        );
    }

    public static Pattern namePattern3to256() {
        return Pattern.compile(
                "^[А-ЩЬЮЯҐЄІЇа-щьюяґєіїA-Za-z]{3,256}$"
        );
    }

    public static String[] phonePatterns() {
        return new String[]{
                "+*** *** ** **",
                "+**(***)*** ** **",
                "*** *** ** **",
                "***-***-**-**"
        };
    }

    public static boolean checkPassword(String password, String toCheckPassword) {
        return password.equals(toCheckPassword);
    }

    public static String getRandomStringByPattern(String pattern) {
        RgxGen rgxGen = new RgxGen(pattern);
        return rgxGen.generate();
    }
}

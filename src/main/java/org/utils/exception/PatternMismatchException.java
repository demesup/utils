package org.utils.exception;

import java.util.regex.Pattern;

public class PatternMismatchException extends RuntimeException{
    public PatternMismatchException(Pattern pattern, String string) {
        super("String [" + string + "] does not match to pattern [" + pattern + ']');
    }
}

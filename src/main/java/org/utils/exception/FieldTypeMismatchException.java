package org.utils.exception;

public class FieldTypeMismatchException extends RuntimeException {
    public FieldTypeMismatchException(Class<?> fieldGeneric, Class<?> receivedGeneric) {
        super("Required " + fieldGeneric + ", received " + receivedGeneric);
    }
}

package org.utils.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ObjectInitializationException extends RuntimeException {
    public ObjectInitializationException(String message) {
        super(message);
    }
}

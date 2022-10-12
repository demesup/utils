package org.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.utils.exception.NullValueException;
import org.utils.exception.PatternMismatchException;

@Getter
@AllArgsConstructor
public enum PossibleException {
    NOT_NULL_VALUE( NullValueException.class),
    PATTERN_MISMATCH_EXCEPTION(PatternMismatchException.class);

    final Class<? extends RuntimeException> exception;
}

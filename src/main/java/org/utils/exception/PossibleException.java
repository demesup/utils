package org.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PossibleException {
    NOT_NULL_VALUE( NullValueException.class),
    PATTERN_MISMATCH_EXCEPTION(PatternMismatchException.class);

    final Class<? extends RuntimeException> exception;
}

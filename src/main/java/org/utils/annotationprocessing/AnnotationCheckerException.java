package org.utils.annotationprocessing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.utils.annotationprocessing.annotation.NotNullValue;
import org.utils.annotationprocessing.annotation.Patterned;
import org.utils.exception.NullValueException;
import org.utils.exception.PatternMismatchException;

import java.lang.annotation.Annotation;

@AllArgsConstructor
@Getter
public enum AnnotationCheckerException {
    NOT_NULL_VALUE(NotNullValue.class, NullValueException.class, Checker.IS_NULL_OR_EMPTY),
    PATTERNED(Patterned.class, PatternMismatchException.class, Checker.CHECK_MATCH_TO_PATTERN);

    final Class<? extends Annotation> annotationClass;
    final Class<? extends RuntimeException> exceptionClass;
    final Checker checker;
}
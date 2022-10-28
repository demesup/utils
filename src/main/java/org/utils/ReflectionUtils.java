package org.utils;

import lombok.extern.slf4j.Slf4j;
import org.utils.annotationprocessing.AnnotationCheckerException;
import org.utils.annotationprocessing.Checker;
import org.utils.annotationprocessing.annotation.Patterned;
import org.utils.exception.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;

import static org.utils.Utils.fieldIsAnnotatedBy;

@Slf4j
public class ReflectionUtils {

    public static List<Field> getNonStaticFields(Class<?> cl) {
        return Arrays.stream(cl.getDeclaredFields()).filter(f ->
                !Modifier.isStatic(f.getModifiers())).toList();
    }

    public static <T> T validatedValue(T value,
                                       Field field,
                                       boolean validator,
                                       Class<? extends Annotation> annotation,
                                       RuntimeException exception) {
        validate(field, validator, annotation, exception);
        return value;
    }

    public static void validate(Field field,
                                boolean validator,
                                Class<? extends Annotation> annotation,
                                RuntimeException exception) {
        if (validator && fieldIsAnnotatedBy(annotation, field)) {
            throw exception;
        }
    }

    public static List<AnnotationCheckerException> getMyAnnotations(Field field) {
        List<AnnotationCheckerException> annotations = new ArrayList<>();
        AnnotationCheckerException[] values = AnnotationCheckerException.values();
        for (AnnotationCheckerException value : values) {
            if (fieldIsAnnotatedBy(value.getAnnotationClass(), field)) annotations.add(value);
        }
        return annotations;
    }

    public static boolean isNullOrEmpty(Object o) {
        return o == null || o.toString().length() == 0;
    }

    public static Field getField(Class<?> cl, String fieldName) throws NoSuchFieldException {
        return cl.getDeclaredField(fieldName);
    }

    public static <T> void checkFields(T obj) {
        List<Field> fields = getNonStaticFields(obj.getClass());

        fields.forEach(field -> {
            try {
                checkField(field.get(obj), field);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new ObjectInitializationException(e.getMessage());
            }
        });
    }

    public static <T> T checkField(T value, String fieldName, Class<T> cl) throws NoSuchFieldException {

        Field field = getField(cl, fieldName);

        return checkField(value, field);
    }

    public static <T> T checkField(T value, Field field) throws NoSuchFieldException {

        List<AnnotationCheckerException> annotations = getMyAnnotations(field);

        annotations.forEach(annotation -> {
            try {
                RuntimeException possibleException = getPossibleException(annotation, value, field);
                boolean checker = invokeChecker(getChecker(annotation), value, field);
                validate(
                        field,
                        checker,
                        annotation.getAnnotationClass(),
                        possibleException
                );
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | ClassCastException | PatternMismatchException e) {
                throw new ObjectInitializationException(e.getMessage());
            }
        });
        return value;
    }


    private static <T> RuntimeException getPossibleException(AnnotationCheckerException annotation, T value, Field field) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return getExceptionByEnum(annotation, value, field);
    }

    private static <T> RuntimeException getExceptionByEnum(AnnotationCheckerException annotation, T value, Field field) {
        switch (annotation) {
            case NOT_NULL_VALUE -> {
                return new NullValueException(value, field);
            }
            case PATTERNED -> {
                return new PatternMismatchException(field.getAnnotation(Patterned.class).pattern().getPattern(), value.toString());
            }
            default ->
                    throw new ObjectInitializationException("Annotation " + annotation + "does not have defined exception");
        }
    }

    private static Class<? extends RuntimeException> getExceptionClass(AnnotationCheckerException annotation) {
        var exception = annotation.getExceptionClass();
        if (exception != null) return exception;
        throw new ExceptionNotFoundException("Couldn't find exception for " + annotation.getAnnotationClass().getName());
    }

    private static Checker getChecker(AnnotationCheckerException annotation) {
        return annotation.getChecker();
    }

    private static <T> boolean invokeChecker(Checker checker, T value, Field field) {
        if (checker == null) throw new CheckerException("Null value");

        switch (checker) {
            case IS_NULL_OR_EMPTY -> {
                return isNullOrEmpty(value);
            }
            case CHECK_MATCH_TO_PATTERN -> {
                return !Patterns.checkMatchToPattern(
                        field.getAnnotation(Patterned.class).pattern().getPattern(), value.toString()
                );
            }
            default -> {
                log.debug("No checker method found for " + checker);
                return false;
            }
        }
    }
}
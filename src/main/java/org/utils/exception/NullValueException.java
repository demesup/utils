package org.utils.exception;

import java.lang.reflect.Field;

public class NullValueException extends ObjectInitializationException {
  public NullValueException(Object value, Field field) {
    super("Field '" + field.getName() + "' requires not null or empty value, received value: '" + value.toString() + "'");
  }
}

package org.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.regex.Pattern;

import static org.utils.Patterns.*;


@Getter
@AllArgsConstructor
public enum BasePattern {
    EMAIL(emailPattern()),
    PHONE(phonePattern()),
    PASSWORD(passwordPattern1char1Capital1number8()),
    NAME(namePattern3to256());

    final Pattern pattern;
}

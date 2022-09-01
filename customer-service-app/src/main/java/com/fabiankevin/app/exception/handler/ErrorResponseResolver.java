package com.fabiankevin.app.exception.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ErrorResponseResolver {
    private final ErrorResponseProperties errorResponseProperties;
    public ErrorResponse resolve(RuntimeException exception) {
        String exceptionName = StringUtils.upperCase(camelToSnake(exception.getClass().getSimpleName()));

        return ErrorResponse.builder()
                .error(errorResponseProperties.getErrorResponse(exceptionName))
                .build();
    }
    private static String camelToSnake(String str) {
        // Regular Expression
        String regex = "([a-z])([A-Z]+)";
        // Replacement string
        String replacement = "$1_$2";
        str = str.replaceAll(regex, replacement).toLowerCase();
        return str;
    }
}

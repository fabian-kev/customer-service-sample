package com.fabiankevin.app.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;
import java.util.Map;

@Configuration
@EnableConfigurationProperties({ErrorResponseProperties.class})
@ConfigurationProperties(prefix = "application.common")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseProperties {
    private String name;
    private static final String FALLBACK = "FALLBACK";
    private Map<String, ErrorResponse.Error> errorResponses;
    public ErrorResponse.Error getErrorResponse(String code) {
        return errorResponses.getOrDefault(code, errorResponses.get(FALLBACK)).toBuilder()
                .timestamp(OffsetDateTime.now())
                .build();
    }
}

package org.george.moviecriticapi.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("omdb")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OMDBPropertiesConfig {
    private String apiKey;
}

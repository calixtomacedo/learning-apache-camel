package br.com.cmdev.apachecamel.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties("router.config")
public class RouterPropertiesConfig {

    @NotBlank
    private String personUrl;

    @NotBlank
    private String addressUrl;
}



package com.dati.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(value = CorsPropertiesGateway.class)
@Configuration
public class CorsConfigGateway extends CorsConfig{
}

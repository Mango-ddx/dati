package com.dati.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@EnableConfigurationProperties(value = CorsPropertiesLogin.class)
@Configuration
public class CorsConfigLogin extends CorsConfig{
}

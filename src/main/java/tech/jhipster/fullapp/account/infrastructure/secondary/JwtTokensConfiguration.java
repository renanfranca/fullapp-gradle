package tech.jhipster.fullapp.account.infrastructure.secondary;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtTokensProperties.class)
class JwtTokensConfiguration {}

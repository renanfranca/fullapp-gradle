package tech.jhipster.fullapp.wire.postgresql.infrastructure.secondary;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "tech.jhipster.fullapp" }, enableDefaultTransactions = false)
class DatabaseConfiguration {}

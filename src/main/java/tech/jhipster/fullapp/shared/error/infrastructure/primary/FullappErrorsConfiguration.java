package tech.jhipster.fullapp.shared.error.infrastructure.primary;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
class FullappErrorsConfiguration {

  @Bean("applicationErrorMessageSource")
  MessageSource applicationErrorMessageSource() {
    ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();

    source.setBasename("classpath:/messages/errors/fullapp-errors-messages");
    source.setDefaultEncoding("UTF-8");

    return source;
  }
}

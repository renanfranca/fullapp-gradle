package tech.jhipster.fullapp.wire.logstash.infrastructure.secondary;

import static org.assertj.core.api.Assertions.assertThat;

import tech.jhipster.fullapp.UnitTest;
import org.junit.jupiter.api.Test;

@UnitTest
class LogstashTcpPropertiesTest {

  @Test
  void shouldDisableByDefault() {
    assertThat(new LogstashTcpProperties().isEnabled()).isFalse();
  }
}

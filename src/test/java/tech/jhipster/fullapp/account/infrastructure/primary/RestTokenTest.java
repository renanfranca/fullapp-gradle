package tech.jhipster.fullapp.account.infrastructure.primary;

import static tech.jhipster.fullapp.account.domain.TokensFixture.*;
import static org.assertj.core.api.Assertions.*;

import tech.jhipster.fullapp.JsonHelper;
import tech.jhipster.fullapp.UnitTest;
import org.junit.jupiter.api.Test;

@UnitTest
class RestTokenTest {

  @Test
  void shouldConvertFromDomain() {
    assertThat(JsonHelper.writeAsString(RestToken.from(token()))).isEqualTo(json());
  }

  private String json() {
    return "{\"id_token\":\"token\"}";
  }
}

package tech.jhipster.fullapp.sample.infrastructure.primary.beer;

import static tech.jhipster.fullapp.sample.domain.beer.BeersFixture.*;
import static org.assertj.core.api.Assertions.*;

import tech.jhipster.fullapp.JsonHelper;
import tech.jhipster.fullapp.UnitTest;
import org.junit.jupiter.api.Test;

@UnitTest
class RestBeerTest {

  @Test
  void shouldSerializeToJson() {
    assertThat(JsonHelper.writeAsString(RestBeer.from(beer()))).isEqualTo(json());
  }

  static String json() {
    return """
        {\
        "id":"5ea9bbb1-3a55-4701-9006-3bbd2878f241",\
        "name":"Cloak of feathers",\
        "unitPrice":8.53\
        }\
        """;
  }
}

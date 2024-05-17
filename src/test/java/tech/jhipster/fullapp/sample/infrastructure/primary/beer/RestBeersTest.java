package tech.jhipster.fullapp.sample.infrastructure.primary.beer;

import static org.assertj.core.api.Assertions.*;

import tech.jhipster.fullapp.JsonHelper;
import tech.jhipster.fullapp.UnitTest;
import tech.jhipster.fullapp.sample.domain.beer.Beers;
import tech.jhipster.fullapp.sample.domain.beer.BeersFixture;
import java.util.List;
import org.junit.jupiter.api.Test;

@UnitTest
class RestBeersTest {

  @Test
  void shouldSerializeToJson() {
    assertThat(JsonHelper.writeAsString(RestBeers.from(new Beers(List.of(BeersFixture.beer()))))).isEqualTo(json());
  }

  private String json() {
    return "{\"beers\":[" + RestBeerTest.json() + "]}";
  }
}

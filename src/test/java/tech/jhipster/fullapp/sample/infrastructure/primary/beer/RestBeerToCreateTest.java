package tech.jhipster.fullapp.sample.infrastructure.primary.beer;

import static tech.jhipster.fullapp.BeanValidationAssertions.*;
import static org.assertj.core.api.Assertions.*;

import tech.jhipster.fullapp.UnitTest;
import tech.jhipster.fullapp.JsonHelper;
import tech.jhipster.fullapp.sample.domain.beer.BeersFixture;
import org.junit.jupiter.api.Test;

@UnitTest
class RestBeerToCreateTest {

  @Test
  void shouldDeserializeFromJson() {
    assertThat(JsonHelper.readFromJson(json(), RestBeerToCreate.class).toDomain())
      .usingRecursiveComparison()
      .isEqualTo(BeersFixture.beerToCreate());
  }

  private String json() {
    return """
        {
          "name": "Cloak of feathers",
          "unitPrice": 8.53
        }
        """;
  }

  @Test
  void shouldNotValidateEmptyBean() {
    assertThatBean(new RestBeerToCreate(null, null)).hasInvalidProperty("name").and().hasInvalidProperty("unitPrice");
  }
}

package tech.jhipster.fullapp.sample.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.fullapp.sample.domain.beer.BeersFixture.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.fullapp.UnitTest;

@UnitTest
class BeerEntityTest {

  @Test
  void shouldConvertFromAndToDomain() {
    assertThat(BeerEntity.from(beer()).toDomain()).usingRecursiveComparison().isEqualTo(beer());
  }
}

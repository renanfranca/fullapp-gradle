package tech.jhipster.fullapp.sample.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.fullapp.sample.domain.BeersIdentityFixture.*;
import static tech.jhipster.fullapp.sample.domain.beer.BeersFixture.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tech.jhipster.fullapp.IntegrationTest;

@Transactional
@IntegrationTest
class JpaBeersRepositoryIT {

  @Autowired
  private JpaBeersRepository beers;

  @Test
  void shouldSaveAndGetBeer() {
    beers.saveAndFlush(BeerEntity.from(beer()));

    assertThat(beers.findById(cloackOfFeathersId().get()).get().toDomain()).usingRecursiveComparison().isEqualTo(beer());
  }
}

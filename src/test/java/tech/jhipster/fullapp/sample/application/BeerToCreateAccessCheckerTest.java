package tech.jhipster.fullapp.sample.application;

import static tech.jhipster.fullapp.sample.domain.beer.BeersFixture.*;
import static tech.jhipster.fullapp.shared.kipe.application.TestAuthentications.*;
import static org.assertj.core.api.Assertions.*;

import tech.jhipster.fullapp.UnitTest;
import tech.jhipster.fullapp.shared.kipe.application.AccessContext;
import tech.jhipster.fullapp.shared.kipe.application.FullappAuthorizations;
import java.util.List;
import org.junit.jupiter.api.Test;

@UnitTest
class BeerToCreateAccessCheckerTest {

  private static final BeerToCreateAccessChecker checker = new BeerToCreateAccessChecker(
    new FullappAuthorizations(List.of(new BeersAccessesConfiguration().beersAccesses()))
  );

  @Test
  void shouldNotAuthorizedUnauthorizedAction() {
    assertThat(checker.can(AccessContext.of(admin(), "unauthorized", beerToCreate()))).isFalse();
  }

  @Test
  void shouldAuthorizedAuthorizedAction() {
    assertThat(checker.can(AccessContext.of(admin(), "create", beerToCreate()))).isTrue();
  }
}

package tech.jhipster.fullapp.sample.application;

import tech.jhipster.fullapp.sample.domain.beer.BeerToCreate;
import tech.jhipster.fullapp.shared.kipe.application.AccessChecker;
import tech.jhipster.fullapp.shared.kipe.application.AccessContext;
import tech.jhipster.fullapp.shared.kipe.application.FullappAuthorizations;
import org.springframework.stereotype.Component;

@Component
class BeerToCreateAccessChecker implements AccessChecker<BeerToCreate> {

  private final FullappAuthorizations authorizations;

  public BeerToCreateAccessChecker(FullappAuthorizations authorizations) {
    this.authorizations = authorizations;
  }

  @Override
  public boolean can(AccessContext<BeerToCreate> access) {
    return authorizations.allAuthorized(access.authentication(), access.action(), BeerResource.BEERS);
  }
}

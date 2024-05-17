package tech.jhipster.fullapp.sample.application;

import tech.jhipster.fullapp.sample.domain.BeerId;
import tech.jhipster.fullapp.shared.kipe.application.AccessChecker;
import tech.jhipster.fullapp.shared.kipe.application.AccessContext;
import tech.jhipster.fullapp.shared.kipe.application.FullappAuthorizations;
import org.springframework.stereotype.Component;

@Component
class BeerIdAccessChecker implements AccessChecker<BeerId> {

  private final FullappAuthorizations authorizations;

  public BeerIdAccessChecker(FullappAuthorizations authorizations) {
    this.authorizations = authorizations;
  }

  @Override
  public boolean can(AccessContext<BeerId> access) {
    return authorizations.allAuthorized(access.authentication(), access.action(), BeerResource.BEERS);
  }
}

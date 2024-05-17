package tech.jhipster.fullapp.sample.domain.beer;

import tech.jhipster.fullapp.sample.domain.Amount;
import tech.jhipster.fullapp.sample.domain.BeerId;
import tech.jhipster.fullapp.shared.error.domain.Assert;

public record BeerToCreate(BeerName name, Amount unitPrice) {
  public BeerToCreate {
    Assert.notNull("name", name);
    Assert.notNull("unitPrice", unitPrice);
  }

  public Beer create() {
    return Beer.builder().id(BeerId.newId()).name(name()).unitPrice(unitPrice()).sellingState(BeerSellingState.SOLD).build();
  }
}

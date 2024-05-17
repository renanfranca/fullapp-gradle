package tech.jhipster.fullapp.sample.domain.order;

import tech.jhipster.fullapp.sample.domain.Amount;
import tech.jhipster.fullapp.sample.domain.BeerId;
import tech.jhipster.fullapp.shared.error.domain.Assert;

public record OrderedBeer(BeerId beer, Amount unitPrice) {
  public OrderedBeer {
    Assert.notNull("beer", beer);
    Assert.notNull("unitPrice", unitPrice);
  }
}

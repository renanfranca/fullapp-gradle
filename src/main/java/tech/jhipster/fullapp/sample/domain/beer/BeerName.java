package tech.jhipster.fullapp.sample.domain.beer;

import tech.jhipster.fullapp.shared.error.domain.Assert;

public record BeerName(String name) {
  public BeerName {
    Assert.field("name", name).notBlank().maxLength(255);
  }

  public String get() {
    return name();
  }
}

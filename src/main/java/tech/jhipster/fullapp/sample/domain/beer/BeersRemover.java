package tech.jhipster.fullapp.sample.domain.beer;

import tech.jhipster.fullapp.sample.domain.BeerId;
import tech.jhipster.fullapp.shared.error.domain.Assert;

public class BeersRemover {

  private final BeersRepository beers;

  public BeersRemover(BeersRepository beers) {
    Assert.notNull("beers", beers);

    this.beers = beers;
  }

  public void remove(BeerId id) {
    Assert.notNull("id", id);

    Beer beer = beers.get(id).orElseThrow(() -> new UnknownBeerException(id));

    beer.removeFromSell();
    beers.save(beer);
  }
}

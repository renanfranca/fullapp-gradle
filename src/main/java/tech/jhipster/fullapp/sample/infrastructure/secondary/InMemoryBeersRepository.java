package tech.jhipster.fullapp.sample.infrastructure.secondary;

import tech.jhipster.fullapp.sample.domain.BeerId;
import tech.jhipster.fullapp.sample.domain.beer.Beer;
import tech.jhipster.fullapp.sample.domain.beer.BeerSellingState;
import tech.jhipster.fullapp.sample.domain.beer.Beers;
import tech.jhipster.fullapp.sample.domain.beer.BeersRepository;
import tech.jhipster.fullapp.shared.error.domain.Assert;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import org.springframework.stereotype.Repository;

@Repository
class InMemoryBeersRepository implements BeersRepository {

  private final Map<BeerId, Beer> beers = new ConcurrentHashMap<>();

  @Override
  public void save(Beer beer) {
    Assert.notNull("beer", beer);

    beers.put(beer.id(), beer);
  }

  @Override
  public Beers catalog() {
    return new Beers(beers.values().stream().filter(soldBeer()).toList());
  }

  private Predicate<Beer> soldBeer() {
    return beer -> beer.sellingState() == BeerSellingState.SOLD;
  }

  @Override
  public Optional<Beer> get(BeerId beer) {
    Assert.notNull("beer", beer);

    return Optional.ofNullable(beers.get(beer));
  }

  public void clear() {
    beers.clear();
  }
}

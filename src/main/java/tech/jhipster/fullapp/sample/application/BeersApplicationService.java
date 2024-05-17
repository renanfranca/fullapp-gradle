package tech.jhipster.fullapp.sample.application;

import tech.jhipster.fullapp.sample.domain.BeerId;
import tech.jhipster.fullapp.sample.domain.beer.Beer;
import tech.jhipster.fullapp.sample.domain.beer.BeerToCreate;
import tech.jhipster.fullapp.sample.domain.beer.Beers;
import tech.jhipster.fullapp.sample.domain.beer.BeersCreator;
import tech.jhipster.fullapp.sample.domain.beer.BeersRemover;
import tech.jhipster.fullapp.sample.domain.beer.BeersRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BeersApplicationService {

  private final BeersRepository beers;
  private final BeersCreator creator;
  private final BeersRemover remover;

  public BeersApplicationService(BeersRepository beers) {
    this.beers = beers;

    creator = new BeersCreator(beers);
    remover = new BeersRemover(beers);
  }

  @Transactional
  @PreAuthorize("can('create', #beerToCreate)")
  public Beer create(BeerToCreate beerToCreate) {
    return creator.create(beerToCreate);
  }

  @Transactional
  @PreAuthorize("can('remove', #beer)")
  public void remove(BeerId beer) {
    remover.remove(beer);
  }

  @Transactional(readOnly = true)
  public Beers catalog() {
    return beers.catalog();
  }

}

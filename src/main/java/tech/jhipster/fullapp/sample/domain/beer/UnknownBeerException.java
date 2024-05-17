package tech.jhipster.fullapp.sample.domain.beer;

import tech.jhipster.fullapp.sample.domain.BeerId;

class UnknownBeerException extends RuntimeException {

  public UnknownBeerException(BeerId id) {
    super("Beer " + id.get() + " is unknown");
  }
}

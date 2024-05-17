package tech.jhipster.fullapp.shared.error.infrastructure.primary;

import tech.jhipster.fullapp.shared.error.domain.FullappException;

public final class FullappExceptionFactory {

  private FullappExceptionFactory() {}

  public static final FullappException buildEmptyException() {
    return FullappException.builder(null).build();
  }
}

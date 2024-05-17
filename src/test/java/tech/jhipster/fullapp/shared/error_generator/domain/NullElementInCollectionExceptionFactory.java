package tech.jhipster.fullapp.shared.error_generator.domain;

import tech.jhipster.fullapp.shared.error.domain.NullElementInCollectionException;

public final class NullElementInCollectionExceptionFactory {

  private NullElementInCollectionExceptionFactory() {}

  public static NullElementInCollectionException nullElementInCollection() {
    return new NullElementInCollectionException("field");
  }
}

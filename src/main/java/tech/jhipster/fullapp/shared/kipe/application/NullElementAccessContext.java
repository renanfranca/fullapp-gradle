package tech.jhipster.fullapp.shared.kipe.application;

import tech.jhipster.fullapp.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;
import tech.jhipster.fullapp.shared.error.domain.Assert;
import org.springframework.security.core.Authentication;

@ExcludeFromGeneratedCodeCoverage(reason = "Untested null object structure")
record NullElementAccessContext<T>(Authentication authentication, String action) implements AccessContext<T> {
  public NullElementAccessContext {
    Assert.notNull("authentication", authentication);
    Assert.notBlank("action", action);
  }

  @Override
  public T element() {
    return null;
  }
}

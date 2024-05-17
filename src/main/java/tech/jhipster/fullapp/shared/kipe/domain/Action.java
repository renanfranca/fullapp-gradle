package tech.jhipster.fullapp.shared.kipe.domain;

import tech.jhipster.fullapp.shared.error.domain.Assert;

public record Action(String action) {
  public Action {
    Assert.notBlank("action", action);
  }
  
  @Override
  public String toString() {
    return action();
  }
}

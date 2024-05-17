package tech.jhipster.fullapp.account.domain;

import tech.jhipster.fullapp.shared.error.domain.Assert;

public record Token(String token) {
  public Token {
    Assert.notBlank("token", token);
  }

  public String get() {
    return token();
  }

  public String bearer() {
    return "Bearer " + token();
  }
}

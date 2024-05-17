package tech.jhipster.fullapp.account.domain;

public interface TokensRepository {
  Token buildToken(AuthenticationQuery query);
}

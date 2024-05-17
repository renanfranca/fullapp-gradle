package tech.jhipster.fullapp.account.application;

import tech.jhipster.fullapp.account.domain.AuthenticationQuery;
import tech.jhipster.fullapp.account.domain.Token;
import tech.jhipster.fullapp.account.domain.TokensRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountApplicationService {

  private final TokensRepository tokens;

  public AccountApplicationService(TokensRepository tokens) {
    this.tokens = tokens;
  }

  public Token createToken(AuthenticationQuery query) {
    return tokens.buildToken(query);
  }
}

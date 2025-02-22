package tech.jhipster.fullapp.account.infrastructure.secondary;

import tech.jhipster.fullapp.account.domain.AuthenticationQuery;
import tech.jhipster.fullapp.account.domain.Token;
import tech.jhipster.fullapp.account.domain.TokensRepository;
import tech.jhipster.fullapp.shared.authentication.domain.Role;
import tech.jhipster.fullapp.shared.error.domain.Assert;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Repository;

@Repository
class JwtTokensRepository implements TokensRepository {

  private final SecretKey key;
  private final long validityDuration;
  private final long rememberMeValidityDuration;

  public JwtTokensRepository(JwtTokensProperties properties) {
    key = Keys.hmacShaKeyFor(properties.getJwtBase64Secret().getBytes(StandardCharsets.UTF_8));
    validityDuration = properties.getTokenValidity().toSeconds();
    rememberMeValidityDuration = properties.getRememberMeTokenValidity().toSeconds();
  }

  public Token buildToken(AuthenticationQuery query) {
    Assert.notNull("query", query);

    String token = Jwts
      .builder()
      .subject(query.username().get())
      .claim("auth", buildAuthorities(query))
      .signWith(key, Jwts.SIG.HS512)
      .expiration(getValidity(query))
      .compact();

    return new Token(token);
  }

  private String buildAuthorities(AuthenticationQuery query) {
    return query.roles().stream().map(Role::key).collect(Collectors.joining(","));
  }

  private Date getValidity(AuthenticationQuery query) {
    if (query.rememberMe()) {
      return Date.from(Instant.now().plusSeconds(rememberMeValidityDuration));
    }

    return Date.from(Instant.now().plusSeconds(validityDuration));
  }
}

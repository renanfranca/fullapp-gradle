package tech.jhipster.fullapp.account.infrastructure.primary;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import tech.jhipster.fullapp.account.domain.AuthenticationQuery;
import tech.jhipster.fullapp.account.infrastructure.primary.RestAuthenticationQuery.RestAuthenticationQueryBuilder;
import tech.jhipster.fullapp.shared.authentication.domain.Roles;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@JsonDeserialize(builder = RestAuthenticationQueryBuilder.class)
@Schema(name = "AuthenticationQuery", description = "Query to authenticate au user")
final class RestAuthenticationQuery {

  private final String username;
  private final String password;
  private final boolean rememberMe;

  private RestAuthenticationQuery(RestAuthenticationQueryBuilder builder) {
    username = builder.username;
    password = builder.password;
    rememberMe = builder.rememberMe;
  }

  public Authentication authenticationToken() {
    return new UsernamePasswordAuthenticationToken(getUsername(), getPassword());
  }

  public AuthenticationQuery toDomain(Roles roles) {
    return AuthenticationQuery.builder().username(username).roles(roles).rememberMe(rememberMe);
  }

  @NotNull
  @Size(min = 1, max = 50)
  @Schema(description = "Username of the user to authenticate", requiredMode = RequiredMode.REQUIRED)
  public String getUsername() {
    return username;
  }

  @NotNull
  @Size(min = 4, max = 100)
  @Schema(description = "Password of the user to authenticate", requiredMode = RequiredMode.REQUIRED)
  public String getPassword() {
    return password;
  }

  @Schema(description = "True if the user want to a long live token, false otherwise", requiredMode = RequiredMode.REQUIRED)
  public boolean isRememberMe() {
    return rememberMe;
  }

  @JsonPOJOBuilder(withPrefix = "")
  static class RestAuthenticationQueryBuilder {

    private String username;
    private String password;
    private boolean rememberMe;

    RestAuthenticationQueryBuilder username(String username) {
      this.username = username;

      return this;
    }

    RestAuthenticationQueryBuilder password(String password) {
      this.password = password;

      return this;
    }

    RestAuthenticationQueryBuilder rememberMe(boolean rememberMe) {
      this.rememberMe = rememberMe;

      return this;
    }

    RestAuthenticationQuery build() {
      return new RestAuthenticationQuery(this);
    }
  }
}

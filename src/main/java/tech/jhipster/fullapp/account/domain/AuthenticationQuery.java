package tech.jhipster.fullapp.account.domain;

import tech.jhipster.fullapp.shared.authentication.domain.Roles;
import tech.jhipster.fullapp.shared.authentication.domain.Username;
import tech.jhipster.fullapp.shared.error.domain.Assert;

public final class AuthenticationQuery {

  private final Username username;
  private final Roles roles;
  private final boolean rememberMe;

  private AuthenticationQuery(AuthenticationQueryBuilder builder) {
    Assert.notNull("username", builder.username);
    Assert.notNull("roles", builder.roles);

    username = builder.username;
    roles = builder.roles;
    rememberMe = builder.rememberMe;
  }

  public static AuthenticationQueryUsernameBuilder builder() {
    return new AuthenticationQueryBuilder();
  }

  public Username username() {
    return username;
  }

  public Roles roles() {
    return roles;
  }

  public boolean rememberMe() {
    return rememberMe;
  }

  private static final class AuthenticationQueryBuilder
    implements AuthenticationQueryUsernameBuilder, AuthenticationQueryRolesBuilder, AuthenticationQueryRememberMeBuilder {

    private Username username;
    private Roles roles;
    private boolean rememberMe;

    private AuthenticationQueryBuilder() {}

    @Override
    public AuthenticationQueryRolesBuilder username(Username username) {
      this.username = username;

      return this;
    }

    @Override
    public AuthenticationQueryRememberMeBuilder roles(Roles roles) {
      this.roles = roles;

      return this;
    }

    @Override
    public AuthenticationQuery rememberMe(boolean rememberMe) {
      this.rememberMe = rememberMe;

      return new AuthenticationQuery(this);
    }
  }

  public interface AuthenticationQueryUsernameBuilder {
    AuthenticationQueryRolesBuilder username(Username username);

    default AuthenticationQueryRolesBuilder username(String username) {
      return username(new Username(username));
    }
  }

  public interface AuthenticationQueryRolesBuilder {
    AuthenticationQueryRememberMeBuilder roles(Roles roles);
  }

  public interface AuthenticationQueryRememberMeBuilder {
    AuthenticationQuery rememberMe(boolean rememberMe);
  }
}

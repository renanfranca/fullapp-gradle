package tech.jhipster.fullapp.shared.authentication.infrastructure.primary;

import tech.jhipster.fullapp.shared.authentication.application.NotAuthenticatedUserException;
import tech.jhipster.fullapp.shared.authentication.application.UnknownAuthenticationException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account-exceptions")
class AccountExceptionResource {

  @GetMapping("/not-authenticated")
  public void notAuthenticatedUser() {
    throw new NotAuthenticatedUserException();
  }

  @GetMapping("/unknown-authentication")
  public void unknownAuthentication() {
    throw new UnknownAuthenticationException();
  }
}

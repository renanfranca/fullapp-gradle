package tech.jhipster.fullapp.shared.authentication.infrastructure.primary;

import java.util.Optional;
import org.springframework.security.core.Authentication;

@FunctionalInterface
public interface AuthenticationTokenReader {
  Optional<Authentication> read(String token);
}

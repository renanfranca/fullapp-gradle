package tech.jhipster.fullapp.shared.error.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.Test;
import tech.jhipster.fullapp.UnitTest;
import tech.jhipster.fullapp.shared.error.infrastructure.primary.FullappExceptionFactory;

@UnitTest
class FullappExceptionTest {

  @Test
  void shouldGetMinimalFullappExceptionFromDomain() {
    FullappException exception = FullappException.builder(null).build();

    assertThat(exception.key()).isEqualTo(StandardErrorKey.INTERNAL_SERVER_ERROR);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
    assertThat(exception.getMessage()).isEqualTo("An error occurred");
    assertThat(exception.getCause()).isNull();
    assertThat(exception.parameters()).isEmpty();
  }

  @Test
  void shouldGetMinimalFullappExceptionFromPrimary() {
    FullappException exception = FullappExceptionFactory.buildEmptyException();

    assertThat(exception.key()).isEqualTo(StandardErrorKey.INTERNAL_SERVER_ERROR);
    assertThat(exception.status()).isEqualTo(ErrorStatus.BAD_REQUEST);
    assertThat(exception.getMessage()).isEqualTo("An error occurred");
    assertThat(exception.getCause()).isNull();
    assertThat(exception.parameters()).isEmpty();
  }

  @Test
  void shouldGetFullFullappException() {
    RuntimeException cause = new RuntimeException();
    FullappException exception = FullappException
      .builder(StandardErrorKey.BAD_REQUEST)
      .message("This is an error")
      .cause(cause)
      .addParameter("parameter", "value")
      .addParameters(Map.of("key", "value"))
      .status(ErrorStatus.BAD_REQUEST)
      .build();

    assertThat(exception.key()).isEqualTo(StandardErrorKey.BAD_REQUEST);
    assertThat(exception.status()).isEqualTo(ErrorStatus.BAD_REQUEST);
    assertThat(exception.getMessage()).isEqualTo("This is an error");
    assertThat(exception.getCause()).isEqualTo(cause);
    assertThat(exception.parameters()).containsOnly(entry("parameter", "value"), entry("key", "value"));
  }

  @Test
  void shouldGetTechnicalErrorExceptionFromMessage() {
    FullappException exception = FullappException.technicalError("This is a problem");

    assertThat(exception.getMessage()).isEqualTo("This is a problem");
    assertThat(exception.key()).isEqualTo(StandardErrorKey.INTERNAL_SERVER_ERROR);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }

  @Test
  void shouldGetTechnicalErrorException() {
    RuntimeException cause = new RuntimeException();
    FullappException exception = FullappException.technicalError("This is a problem", cause);

    assertThat(exception.getMessage()).isEqualTo("This is a problem");
    assertThat(exception.key()).isEqualTo(StandardErrorKey.INTERNAL_SERVER_ERROR);
    assertThat(exception.getCause()).isEqualTo(cause);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }

  @Test
  void shouldGetInternalServerErrorShortcut() {
    FullappException exception = FullappException.internalServerError(StandardErrorKey.INTERNAL_SERVER_ERROR).build();

    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }

  @Test
  void shouldGetBadRequestShortcut() {
    FullappException exception = FullappException.badRequest(StandardErrorKey.BAD_REQUEST).build();

    assertThat(exception.status()).isEqualTo(ErrorStatus.BAD_REQUEST);
  }
}

package tech.jhipster.fullapp.shared.error.infrastructure.primary;

import static org.mockito.Mockito.*;

import ch.qos.logback.classic.Level;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.MessageSource;
import tech.jhipster.fullapp.Logs;
import tech.jhipster.fullapp.LogsSpy;
import tech.jhipster.fullapp.LogsSpyExtension;
import tech.jhipster.fullapp.UnitTest;
import tech.jhipster.fullapp.shared.error.domain.FullappException;
import tech.jhipster.fullapp.shared.error.domain.StandardErrorKey;

@UnitTest
@ExtendWith(LogsSpyExtension.class)
class FullappErrorsHandlerTest {

  private static final FullappErrorsHandler handler = new FullappErrorsHandler(mock(MessageSource.class));

  @Logs
  private LogsSpy logs;

  @Test
  void shouldLogServerErrorAsError() {
    handler.handleFullappException(
      FullappException.internalServerError(StandardErrorKey.INTERNAL_SERVER_ERROR).message("Oops").build()
    );

    logs.shouldHave(Level.ERROR, "Oops");
  }

  @Test
  void shouldLogClientErrorAsInfo() {
    handler.handleFullappException(FullappException.badRequest(StandardErrorKey.BAD_REQUEST).message("Oops").build());

    logs.shouldHave(Level.INFO, "Oops");
  }
}

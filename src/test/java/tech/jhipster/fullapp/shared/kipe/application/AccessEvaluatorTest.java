package tech.jhipster.fullapp.shared.kipe.application;

import static org.assertj.core.api.Assertions.*;

import ch.qos.logback.classic.Level;
import java.util.List;
import tech.jhipster.fullapp.Logs;
import tech.jhipster.fullapp.LogsSpy;
import tech.jhipster.fullapp.LogsSpyExtension;
import tech.jhipster.fullapp.UnitTest;
import tech.jhipster.fullapp.shared.error.domain.MissingMandatoryValueException;
import tech.jhipster.fullapp.shared.kipe.domain.KipeDummy;
import tech.jhipster.fullapp.shared.kipe.domain.KipeDummyChild;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

@UnitTest
@ExtendWith({ MockitoExtension.class, LogsSpyExtension.class })
class AccessEvaluatorTest {

  @Mock
  private Authentication authentication;

  @Logs
  private LogsSpy logs;

  @Test
  void shouldNotBuildWithoutObjectChecker() {
    assertThatThrownBy(() -> new AccessEvaluator(List.of())).isExactlyInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  void shouldResolveOnDefaultCheckerForNullObject() {
    AccessEvaluator canEvaluator = new AccessEvaluator(List.of(new ObjectAccessChecker()));

    boolean can = canEvaluator.can(authentication, "action", null);

    assertThat(can).isFalse();
    logs.shouldHave(Level.WARN, "using default");
  }

  @Test
  void shouldResolveOnDefaultCheckerForUnknownType() {
    AccessEvaluator canEvaluator = new AccessEvaluator(List.of(new ObjectAccessChecker()));

    boolean can = canEvaluator.can(authentication, "action", "yo");

    assertThat(can).isFalse();
    logs.shouldHave(Level.WARN, "using default");
  }

  @Test
  void shouldGetMatchingEvaluator() {
    AccessEvaluator canEvaluator = new AccessEvaluator(List.of(new ObjectAccessChecker(), new KipeDummyAccessChecker()));

    assertThat(canEvaluator.can(authentication, "action", new KipeDummy("authorized"))).isTrue();
  }

  @Test
  void shouldGetMatchingEvaluatorForChildClass() {
    AccessEvaluator canEvaluator = new AccessEvaluator(List.of(new ObjectAccessChecker(), new KipeDummyAccessChecker()));

    assertThat(canEvaluator.can(authentication, "action", new KipeDummyChild("authorized"))).isTrue();
    logs.shouldHave(Level.INFO, "evaluator", 1);
  }
}

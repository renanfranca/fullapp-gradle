package tech.jhipster.fullapp.shared.kipe.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import tech.jhipster.fullapp.UnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

@UnitTest
class AccessContextFactoryTest {

  @Test
  void shouldGetNullElementAccessContextFromNullElement() {
    assertThat(AccessContextFactory.of(mock(Authentication.class), "action", null)).isExactlyInstanceOf(NullElementAccessContext.class);
  }

  @Test
  void shouldGetElementAccessContextFromActualElement() {
    assertThat(AccessContextFactory.of(mock(Authentication.class), "action", "element")).isExactlyInstanceOf(ElementAccessContext.class);
  }
}

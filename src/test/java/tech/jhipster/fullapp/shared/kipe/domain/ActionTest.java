package tech.jhipster.fullapp.shared.kipe.domain;

import static org.assertj.core.api.Assertions.*;

import tech.jhipster.fullapp.UnitTest;
import org.junit.jupiter.api.Test;

@UnitTest
class ActionTest {

  @Test
  void shouldGetActionAsToString() {
    assertThat(new Action("act")).hasToString("act");
  }
}

package tech.jhipster.fullapp.shared.pagination.infrastructure.primary;

import static tech.jhipster.fullapp.BeanValidationAssertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tech.jhipster.fullapp.UnitTest;
import tech.jhipster.fullapp.shared.pagination.domain.FullappPageable;

@UnitTest
class RestFullappPageableTest {

  @Test
  void shouldConvertToDomain() {
    FullappPageable pageable = pageable().toPageable();

    assertThat(pageable.page()).isEqualTo(1);
    assertThat(pageable.pageSize()).isEqualTo(15);
  }

  @Test
  void shouldNotValidateWithPageUnderZero() {
    RestFullappPageable pageable = pageable();
    pageable.setPage(-1);

    assertThatBean(pageable).hasInvalidProperty("page");
  }

  @Test
  void shouldNotValidateWithSizeAtZero() {
    RestFullappPageable pageable = pageable();
    pageable.setPageSize(0);

    assertThatBean(pageable).hasInvalidProperty("pageSize").withParameter("value", 1L);
  }

  @Test
  void shouldNotValidateWithPageSizeOverHundred() {
    RestFullappPageable pageable = pageable();
    pageable.setPageSize(101);

    assertThatBean(pageable).hasInvalidProperty("pageSize");
  }

  private RestFullappPageable pageable() {
    return new RestFullappPageable(1, 15);
  }
}

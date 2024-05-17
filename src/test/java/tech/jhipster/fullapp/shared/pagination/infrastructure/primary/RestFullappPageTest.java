package tech.jhipster.fullapp.shared.pagination.infrastructure.primary;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.fullapp.shared.pagination.domain.FullappPagesFixture.*;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import tech.jhipster.fullapp.UnitTest;
import tech.jhipster.fullapp.JsonHelper;
import tech.jhipster.fullapp.shared.error.domain.MissingMandatoryValueException;

@UnitTest
class RestFullappPageTest {

  @Test
  void shouldNotConvertWithoutSourcePage() {
    assertThatThrownBy(() -> RestFullappPage.from(null, source -> "test")).isExactlyInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  void shouldNotConvertWithoutMappingFunction() {
    assertThatThrownBy(() -> RestFullappPage.from(page(), null)).isExactlyInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  void shouldMapFromDomainPage() {
    RestFullappPage<String> page = RestFullappPage.from(page(), Function.identity());

    assertThat(page.getContent()).containsExactly("test");
    assertThat(page.getCurrentPage()).isEqualTo(2);
    assertThat(page.getPageSize()).isEqualTo(10);
    assertThat(page.getTotalElementsCount()).isEqualTo(21);
    assertThat(page.getPagesCount()).isEqualTo(3);
  }

  @Test
  void shouldGetPageCountForPageLimit() {
    RestFullappPage<String> page = RestFullappPage.from(pageBuilder().totalElementsCount(3).pageSize(3).build(), Function.identity());

    assertThat(page.getPagesCount()).isEqualTo(1);
  }

  @Test
  void shouldSerializeToJson() {
    assertThat(JsonHelper.writeAsString(RestFullappPage.from(page(), Function.identity()))).isEqualTo(json());
  }

  private String json() {
    return """
        {"content":["test"],\
        "currentPage":2,\
        "pageSize":10,\
        "totalElementsCount":21,\
        "pagesCount":3,\
        "hasPrevious":true,\
        "hasNext":false\
        }\
        """;
  }
}

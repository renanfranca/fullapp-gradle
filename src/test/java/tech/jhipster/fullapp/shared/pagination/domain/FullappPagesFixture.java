package tech.jhipster.fullapp.shared.pagination.domain;

import java.util.List;

import tech.jhipster.fullapp.shared.pagination.domain.FullappPage.FullappPageBuilder;

public final class FullappPagesFixture {

  private FullappPagesFixture() {}

  public static FullappPage<String> page() {
    return pageBuilder().build();
  }

  public static FullappPageBuilder<String> pageBuilder() {
    return FullappPage.builder(List.of("test")).currentPage(2).pageSize(10).totalElementsCount(21);
  }
}

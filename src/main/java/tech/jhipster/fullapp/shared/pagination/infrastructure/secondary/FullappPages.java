package tech.jhipster.fullapp.shared.pagination.infrastructure.secondary;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import tech.jhipster.fullapp.shared.error.domain.Assert;
import tech.jhipster.fullapp.shared.pagination.domain.FullappPage;
import tech.jhipster.fullapp.shared.pagination.domain.FullappPageable;

public final class FullappPages {

  private FullappPages() {}

  public static Pageable from(FullappPageable pagination) {
    return from(pagination, Sort.unsorted());
  }

  public static Pageable from(FullappPageable pagination, Sort sort) {
    Assert.notNull("pagination", pagination);
    Assert.notNull("sort", sort);

    return PageRequest.of(pagination.page(), pagination.pageSize(), sort);
  }

  public static <S, T> FullappPage<T> from(Page<S> springPage, Function<S, T> mapper) {
    Assert.notNull("springPage", springPage);
    Assert.notNull("mapper", mapper);

    return FullappPage
      .builder(springPage.getContent().stream().map(mapper).toList())
      .currentPage(springPage.getNumber())
      .pageSize(springPage.getSize())
      .totalElementsCount(springPage.getTotalElements())
      .build();
  }
}

package tech.jhipster.fullapp.shared.pagination.infrastructure.primary;

import java.util.List;
import java.util.function.Function;

import tech.jhipster.fullapp.shared.error.domain.Assert;
import tech.jhipster.fullapp.shared.pagination.domain.FullappPage;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Schema(name = "Page", description = "Paginated content")
public final class RestFullappPage<T> {

  private final List<T> content;
  private final int currentPage;
  private final int pageSize;
  private final long totalElementsCount;
  private final int pagesCount;
  private final boolean hasPrevious;
  private final boolean hasNext;

  private RestFullappPage(RestFullappPageBuilder<T> builder) {
    content = builder.content;
    currentPage = builder.currentPage;
    pageSize = builder.pageSize;
    totalElementsCount = builder.totalElementsCount;
    pagesCount = builder.pageCount;
    hasPrevious = builder.hasPrevious;
    hasNext = builder.hasNext;
  }

  public static <S, T> RestFullappPage<T> from(FullappPage<S> source, Function<S, T> mapper) {
    Assert.notNull("source", source);
    Assert.notNull("mapper", mapper);

    return new RestFullappPageBuilder<>(source.content().stream().map(mapper).toList())
      .currentPage(source.currentPage())
      .pageSize(source.pageSize())
      .totalElementsCount(source.totalElementsCount())
      .pageCount(source.pageCount())
      .hasPrevious(source.hasPrevious())
      .hasNext(source.hasNext())
      .build();
  }

  @Schema(description = "Page content")
  public List<T> getContent() {
    return content;
  }

  @Schema(description = "Current page (starts at 0)", example = "0", requiredMode = RequiredMode.REQUIRED)
  public int getCurrentPage() {
    return currentPage;
  }

  @Schema(description = "Number of elements on each page", example = "10", requiredMode = RequiredMode.REQUIRED)
  public int getPageSize() {
    return pageSize;
  }

  @Schema(description = "Total number of elements to paginate", example = "100", requiredMode = RequiredMode.REQUIRED)
  public long getTotalElementsCount() {
    return totalElementsCount;
  }

  @Schema(description = "Number of resulting pages", example = "10", requiredMode = RequiredMode.REQUIRED)
  public int getPagesCount() {
    return pagesCount;
  }

  @Schema(description = "True is there is a previous page, false otherwise", requiredMode = RequiredMode.REQUIRED)
  public boolean getHasPrevious() {
    return hasPrevious;
  }

  @Schema(description = "True is there is a next page, false otherwise", requiredMode = RequiredMode.REQUIRED)
  public boolean getHasNext() {
    return hasNext;
  }

  private static final class RestFullappPageBuilder<T> {

    private final List<T> content;
    private int currentPage;
    private int pageSize;
    private long totalElementsCount;
    private int pageCount;
    private boolean hasPrevious;
    private boolean hasNext;

    private RestFullappPageBuilder(List<T> content) {
      this.content = content;
    }

    public RestFullappPageBuilder<T> pageSize(int pageSize) {
      this.pageSize = pageSize;

      return this;
    }

    public RestFullappPageBuilder<T> currentPage(int currentPage) {
      this.currentPage = currentPage;

      return this;
    }

    public RestFullappPageBuilder<T> totalElementsCount(long totalElementsCount) {
      this.totalElementsCount = totalElementsCount;

      return this;
    }

    public RestFullappPageBuilder<T> pageCount(int pageCount) {
      this.pageCount = pageCount;

      return this;
    }

    public RestFullappPageBuilder<T> hasPrevious(boolean hasPrevious) {
      this.hasPrevious = hasPrevious;

      return this;
    }

    public RestFullappPageBuilder<T> hasNext(boolean hasNext) {
      this.hasNext = hasNext;

      return this;
    }

    public RestFullappPage<T> build() {
      return new RestFullappPage<>(this);
    }
  }
}

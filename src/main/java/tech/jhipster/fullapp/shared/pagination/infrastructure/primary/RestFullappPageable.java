package tech.jhipster.fullapp.shared.pagination.infrastructure.primary;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.jhipster.fullapp.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;
import tech.jhipster.fullapp.shared.pagination.domain.FullappPageable;

@Schema(name = "FullappPageable", description = "Pagination information")
public class RestFullappPageable {

  private int page;
  private int pageSize = 10;

  @ExcludeFromGeneratedCodeCoverage
  public RestFullappPageable() {}

  public RestFullappPageable(int page, int pageSize) {
    this.page = page;
    this.pageSize = pageSize;
  }

  @Min(value = 0)
  @Schema(description = "Page to display (starts at 0)", example = "0")
  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  @Min(value = 1)
  @Max(value = 100)
  @Schema(description = "Number of elements on each page", example = "10")
  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public FullappPageable toPageable() {
    return new FullappPageable(page, pageSize);
  }
}

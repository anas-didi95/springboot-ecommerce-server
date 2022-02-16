package com.anasdidi.ecommerce.common;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
public class PaginationDTO extends BaseDTO {

  private final Long totalElements;
  private final Integer totalPages;
  private final Integer pageNumber;
  private final Integer pageSize;
}

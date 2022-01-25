package com.anasdidi.ecommerce.service.producttype;

import java.time.Instant;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public final class ProductTypeDTO {

  private final String code;
  private final String description;
  private final Boolean isDeleted;
  private final String lastModifiedBy;
  private final Instant lastModifiedDate;
  private final Long version;
}

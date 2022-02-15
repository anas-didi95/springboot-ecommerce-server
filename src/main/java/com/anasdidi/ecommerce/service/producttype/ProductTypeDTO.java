package com.anasdidi.ecommerce.service.producttype;

import java.time.Instant;

import com.anasdidi.ecommerce.common.BaseDTO;

import graphql.schema.DataFetchingEnvironment;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
public final class ProductTypeDTO extends BaseDTO {

  private final String code;
  private final String description;
  private final Boolean isDeleted;
  private final String lastModifiedBy;
  private final Instant lastModifiedDate;
  private final Long version;

  public String getLastModifiedDate(DataFetchingEnvironment env) {
    return lastModifiedDate.toString();
  }
}

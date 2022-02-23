package com.anasdidi.ecommerce.service.product;

import java.math.BigDecimal;
import java.time.Instant;

import com.anasdidi.ecommerce.common.BaseDTO;
import com.anasdidi.ecommerce.service.producttype.ProductTypeDTO;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
public class ProductDTO extends BaseDTO {

  private final String id;
  private final String title;
  private final BigDecimal price;
  private final String description;
  private final String productTypeCode;
  private final Boolean isDeleted;
  private final String lastModifiedBy;
  private final Instant lastModifiedDate;
  private final Long version;

  public ProductTypeDTO getProductType() {
    return null;
  }
}

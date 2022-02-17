package com.anasdidi.ecommerce.service.product;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Table("t_product")
@Data
@Builder
public class Product {

  @Column("id")
  private String id;

  @Column("title")
  private String title;

  @Column("price")
  private BigDecimal price;

  @Column("dscp")
  private String description;

  @Column("product_type_cd")
  private String productTypeCode;

  @Column("is_del")
  private Boolean isDeleted;

  @Column("last_mod_by")
  private String lastModifiedBy;

  @Column("last_mod_dt")
  private Instant lastModifiedDate;

  @Column("version")
  private Long version;
}

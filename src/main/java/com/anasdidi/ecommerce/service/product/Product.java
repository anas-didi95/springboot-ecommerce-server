package com.anasdidi.ecommerce.service.product;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Table("t_product")
@Data
@Builder
final class Product {

  @Id
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

  @LastModifiedBy
  @Column("last_mod_by")
  private String lastModifiedBy;

  @LastModifiedDate
  @Column("last_mod_dt")
  private Instant lastModifiedDate;

  @Version
  @Column("version")
  private Long version;
}

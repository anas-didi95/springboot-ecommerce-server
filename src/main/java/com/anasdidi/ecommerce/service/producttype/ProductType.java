package com.anasdidi.ecommerce.service.producttype;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Table("t_product_type")
@Data
@Builder
final class ProductType {

  @Id
  @Column("cd")
  private String code;

  @Column("dscp")
  private String description;

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

package com.anasdidi.ecommerce.service.producttype;

final class ProductTypeUtils {

  static final ProductTypeDTO toDTO(ProductType o) {
    return ProductTypeDTO.builder().code(o.getCode()).description(o.getDescription()).isDeleted(o.getIsDeleted())
        .lastModifiedBy(o.getLastModifiedBy()).lastModifiedDate(o.getLastModifiedDate()).version(o.getVersion())
        .build();
  }

  static final ProductType toDomain(ProductTypeDTO o) {
    return ProductType.builder().code(o.getCode()).description(o.getDescription()).isDeleted(o.getIsDeleted())
        .lastModifiedBy(o.getLastModifiedBy()).lastModifiedDate(o.getLastModifiedDate()).version(o.getVersion())
        .build();
  }
}

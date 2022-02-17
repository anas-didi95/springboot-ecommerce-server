package com.anasdidi.ecommerce.service.product;

final class ProductUtils {

  static Product toDomain(ProductDTO o) {
    return Product.builder().id(o.getId()).title(o.getTitle()).description(o.getDescription()).price(o.getPrice())
        .isDeleted(o.getIsDeleted()).lastModifiedBy(o.getLastModifiedBy()).lastModifiedDate(o.getLastModifiedDate())
        .version(o.getVersion()).productTypeCode(o.getProductTypeCode()).build();
  }

  static ProductDTO toDTO(Product o) {
    return ProductDTO.builder().id(o.getId()).title(o.getTitle()).description(o.getDescription()).price(o.getPrice())
        .isDeleted(o.getIsDeleted()).lastModifiedBy(o.getLastModifiedBy()).lastModifiedDate(o.getLastModifiedDate())
        .version(o.getVersion()).productTypeCode(o.getProductTypeCode()).build();
  }
}

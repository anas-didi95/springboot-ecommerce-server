package com.anasdidi.ecommerce.service.product;

import java.util.ArrayList;
import java.util.List;

import com.anasdidi.ecommerce.common.BaseValidator;

import org.springframework.stereotype.Component;

@Component
final class ProductValidator extends BaseValidator<ProductDTO> {

  @Override
  protected List<String> validateCreate(ProductDTO dto) {
    List<String> errorList = new ArrayList<>();

    isMandatoryField(errorList, "title", dto.getTitle());
    isMandatoryField(errorList, "price", dto.getPrice());
    isMandatoryField(errorList, "productTypeCode", dto.getProductTypeCode());
    isMandatoryField(errorList, "description", dto.getDescription());

    return errorList;
  }

  @Override
  protected List<String> validateUpdate(ProductDTO dto) {
    List<String> errorList = new ArrayList<>();

    isMandatoryField(errorList, "title", dto.getTitle());
    isMandatoryField(errorList, "price", dto.getPrice());
    isMandatoryField(errorList, "productTypeCode", dto.getProductTypeCode());
    isMandatoryField(errorList, "description", dto.getDescription());
    isMandatoryField(errorList, "isDeleted", dto.getIsDeleted());
    isMandatoryField(errorList, "version", dto.getVersion());

    return errorList;
  }
}

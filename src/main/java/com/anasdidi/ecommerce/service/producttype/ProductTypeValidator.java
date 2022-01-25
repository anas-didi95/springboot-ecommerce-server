package com.anasdidi.ecommerce.service.producttype;

import java.util.ArrayList;
import java.util.List;

import com.anasdidi.ecommerce.common.BaseValidator;

import org.springframework.stereotype.Component;

@Component
final class ProductTypeValidator extends BaseValidator<ProductTypeDTO> {

  @Override
  protected List<String> validateCreate(ProductTypeDTO dto) {
    List<String> errorList = new ArrayList<>();

    isMandatoryField(errorList, "code", dto.getCode());
    isMandatoryField(errorList, "description", dto.getDescription());

    return errorList;
  }
}

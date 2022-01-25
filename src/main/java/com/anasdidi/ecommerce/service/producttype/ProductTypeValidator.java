package com.anasdidi.ecommerce.service.producttype;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
final class ProductTypeValidator {

  enum Action {
    CREATE
  }

  List<String> validateCreate(ProductTypeDTO dto) {
    List<String> errorList = new ArrayList<>();

    isMandatoryField(errorList, "code", dto.getCode());
    isMandatoryField(errorList, "description", dto.getDescription());

    return errorList;
  }

  void isMandatoryField(List<String> errorList, String field, String value) {
    if (!StringUtils.hasText(value)) {
      errorList.add(String.format("[%s] is mandatory field!", field));
    }
  }
}

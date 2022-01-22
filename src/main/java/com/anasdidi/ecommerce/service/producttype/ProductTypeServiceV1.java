package com.anasdidi.ecommerce.service.producttype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ProductTypeServiceV1 implements ProductTypeService {

  private final ProductTypeRepository productTypeRepository;

  @Autowired
  ProductTypeServiceV1(ProductTypeRepository productTypeRepository) {
    this.productTypeRepository = productTypeRepository;
  }

  @Override
  public ProductTypeDTO create(ProductTypeDTO productTypeDTO) {
    productTypeRepository.count();
    return null;
  }
}

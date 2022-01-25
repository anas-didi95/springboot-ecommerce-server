package com.anasdidi.ecommerce.service.producttype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
class ProductTypeServiceV1 implements ProductTypeService {

  private final Logger logger = LoggerFactory.getLogger(ProductTypeServiceV1.class);
  private final ProductTypeRepository productTypeRepository;

  @Autowired
  ProductTypeServiceV1(ProductTypeRepository productTypeRepository) {
    this.productTypeRepository = productTypeRepository;
  }

  @Override
  public Mono<ProductTypeDTO> create(ProductTypeDTO dto, String logPrefix) {
    ProductType domain = ProductType.builder().code(dto.getCode()).description(dto.getDescription()).build();

    logger.debug("[create]{} {}", logPrefix, dto);

    return productTypeRepository.save(domain).map(result -> ProductTypeDTO.builder().code(result.getCode()).build());
  }
}

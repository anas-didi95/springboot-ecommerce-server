package com.anasdidi.ecommerce.service.product;

import com.anasdidi.ecommerce.common.CommonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
class ProductServiceV1 implements ProductService {

  private final Logger logger = LoggerFactory.getLogger(ProductServiceV1.class);
  private final ProductRepository productRepository;

  @Autowired
  ProductServiceV1(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Mono<ProductDTO> create(ProductDTO dto, String logPrefix) {
    Product domain = ProductUtils.toDomain(dto);
    domain.setId(CommonUtils.generateId());

    logger.debug("[create]{} domain={}", logPrefix, domain);

    return productRepository.save(domain).map(result -> ProductDTO.builder().id(result.getId()).build());
  }
}

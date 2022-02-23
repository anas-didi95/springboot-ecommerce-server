package com.anasdidi.ecommerce.service.product;

import com.anasdidi.ecommerce.common.CommonUtils;
import com.anasdidi.ecommerce.exception.RecordNotFoundException;

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

    logger.debug("[create]{}domain={}", logPrefix, domain);

    return productRepository.save(domain).map(result -> ProductDTO.builder().id(result.getId()).build())
        .doOnError(e -> logger.error("[create]{} domain={}", logPrefix, domain));
  }

  @Override
  public Mono<ProductDTO> update(ProductDTO dto, String logPrefix) {
    logger.debug("[update]{}dto={}", logPrefix, dto);

    return productRepository.findById(dto.getId())
        .switchIfEmpty(Mono.error(new RecordNotFoundException(dto.getId())))
        .map(domain -> {
          domain.setDescription(dto.getDescription());
          domain.setIsDeleted(dto.getIsDeleted());
          domain.setPrice(dto.getPrice());
          domain.setProductTypeCode(dto.getProductTypeCode());
          domain.setTitle(dto.getTitle());
          return domain;
        }).flatMap(productRepository::save)
        .map(result -> ProductDTO.builder().id(result.getId()).build())
        .doOnError(e -> logger.error("[update]{}dto={}", logPrefix, dto));
  }
}

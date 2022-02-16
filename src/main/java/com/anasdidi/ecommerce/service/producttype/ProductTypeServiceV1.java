package com.anasdidi.ecommerce.service.producttype;

import com.anasdidi.ecommerce.common.BaseService;
import com.anasdidi.ecommerce.exception.RecordAlreadyExistedException;
import com.anasdidi.ecommerce.exception.RecordNotFoundException;
import com.anasdidi.ecommerce.exception.VersionNotMatchedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
class ProductTypeServiceV1 extends BaseService implements ProductTypeService {

  private final Logger logger = LoggerFactory.getLogger(ProductTypeServiceV1.class);
  private final ProductTypeRepository productTypeRepository;

  @Autowired
  ProductTypeServiceV1(ProductTypeRepository productTypeRepository) {
    this.productTypeRepository = productTypeRepository;
  }

  @Override
  public Mono<ProductTypeDTO> create(ProductTypeDTO dto, String logPrefix) {
    ProductType domain = ProductType.builder().code(dto.getCode()).description(dto.getDescription())
        .build();

    logger.debug("[create]{}domain={}", logPrefix, domain);

    Mono<Boolean> check = productTypeRepository.existsByCode(domain.getCode()).flatMap(result -> {
      if (result) {
        logger.error("[create]{}domain={}", logPrefix, domain);
        return Mono.error(new RecordAlreadyExistedException(domain.getCode()));
      }
      return Mono.just(result);
    });
    Mono<ProductTypeDTO> save = productTypeRepository.save(domain)
        .map(result -> ProductTypeDTO.builder().code(result.getCode()).build());

    return check.then(save);
  }

  @Override
  public Mono<ProductTypeDTO> update(ProductTypeDTO dto, String logPrefix) {
    return productTypeRepository.findByCode(dto.getCode()).switchIfEmpty(Mono.defer(() -> {
      logger.error("[update]{}dto={}", logPrefix, dto);
      return Mono.error(new RecordNotFoundException(dto.getCode()));
    })).flatMap(domain -> {
      if (domain.getVersion() != dto.getVersion()) {
        logger.error("[update]{}dto={}", logPrefix, dto);
        return Mono.error(
            new VersionNotMatchedException(domain.getVersion(), dto.getVersion()));
      }
      return Mono.just(domain);
    }).map(domain -> {
      domain.setDescription(dto.getDescription());
      domain.setIsDeleted(dto.getIsDeleted());
      return domain;
    }).flatMap(productTypeRepository::save)
        .map(result -> ProductTypeDTO.builder().code(result.getCode()).build());
  }

  @Override
  public Mono<Page<ProductTypeDTO>> getProductTypeList(Integer page, Integer size) {
    Pageable pageable = getPageable(page, size);
    return productTypeRepository.findAllBy(pageable).map(ProductTypeUtils::toDTO).collectList()
        .zipWith(productTypeRepository.count())
        .map(t -> new PageImpl<>(t.getT1(), pageable, t.getT2()));
  }
}

package com.anasdidi.ecommerce.service.producttype;

import java.util.Set;

import com.anasdidi.ecommerce.common.BaseService;
import com.anasdidi.ecommerce.exception.RecordNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
class ProductTypeServiceV1 extends BaseService<ProductType, ProductTypeDTO> implements ProductTypeService {

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

    Mono<Boolean> check = productTypeRepository.existsByCode(domain.getCode())
        .flatMap(result -> checkRecordExist(result, domain.getCode()));
    Mono<ProductTypeDTO> save = productTypeRepository.save(domain)
        .map(result -> ProductTypeDTO.builder().code(result.getCode()).build());

    return check.then(save).doOnError(e -> logger.error("[create]{}domain={}", logPrefix, domain));
  }

  @Override
  public Mono<ProductTypeDTO> update(ProductTypeDTO dto, String logPrefix) {
    logger.debug("[update]{}dto={}", logPrefix, dto);

    return productTypeRepository.findByCode(dto.getCode())
        .switchIfEmpty(Mono.error(new RecordNotFoundException(dto.getCode())))
        .flatMap(domain -> checkRecordVersion(domain, domain.getVersion(), dto.getVersion()))
        .map(domain -> merge(domain, dto))
        .flatMap(productTypeRepository::save)
        .map(result -> ProductTypeDTO.builder().code(result.getCode()).build())
        .doOnError(e -> logger.error("[update]{}dto={}", logPrefix, dto));
  }

  @Override
  public ProductType merge(ProductType domain, ProductTypeDTO dto) {
    domain.setDescription(dto.getDescription());
    domain.setIsDeleted(dto.getIsDeleted());
    return domain;
  }

  @Override
  public Mono<Page<ProductTypeDTO>> getProductTypeList(Integer page, Integer size) {
    Pageable pageable = getPageable(page, size, Sort.by(Direction.ASC, "code"));
    return productTypeRepository.findAllBy(pageable).map(ProductTypeUtils::toDTO).collectList()
        .zipWith(productTypeRepository.count())
        .map(t -> new PageImpl<>(t.getT1(), pageable, t.getT2()));
  }

  @Override
  public Flux<ProductTypeDTO> getProductTypeList() {
    return productTypeRepository.findAllByOrderByCodeAsc().map(ProductTypeUtils::toDTO);
  }

  @Override
  public Flux<ProductTypeDTO> getProductTypeList(Set<String> codeList) {
    return productTypeRepository.findAllByCodeIn(codeList).map(ProductTypeUtils::toDTO);
  }
}

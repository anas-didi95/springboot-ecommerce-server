package com.anasdidi.ecommerce.service.product;

import com.anasdidi.ecommerce.common.BaseService;
import com.anasdidi.ecommerce.common.CommonConstants;
import com.anasdidi.ecommerce.common.CommonUtils;
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
class ProductServiceV1 extends BaseService<Product, ProductDTO> implements ProductService {

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

    return productRepository.save(domain)
        .map(result -> ProductDTO.builder().id(result.getId()).build())
        .doOnError(e -> logger.error("[create]{} domain={}", logPrefix, domain));
  }

  @Override
  public Mono<ProductDTO> update(ProductDTO dto, String logPrefix) {
    logger.debug("[update]{}dto={}", logPrefix, dto);

    return productRepository.findById(dto.getId())
        .switchIfEmpty(Mono.error(new RecordNotFoundException(dto.getId())))
        .flatMap(domain -> checkRecordVersion(domain, domain.getVersion(), dto.getVersion()))
        .map(domain -> merge(domain, dto))
        .flatMap(productRepository::save)
        .map(result -> ProductDTO.builder().id(result.getId()).build())
        .doOnError(e -> logger.error("[update]{}dto={}", logPrefix, dto));
  }

  @Override
  public Product merge(Product domain, ProductDTO dto) {
    domain.setDescription(dto.getDescription());
    domain.setIsDeleted(dto.getIsDeleted());
    domain.setPrice(dto.getPrice());
    domain.setProductTypeCode(dto.getProductTypeCode());
    domain.setTitle(dto.getTitle());
    return domain;
  }

  @Override
  public Flux<ProductDTO> getProductList() {
    return productRepository.findAllByOrderByTitleAsc().map(ProductUtils::toDTO);
  }

  @Override
  public Mono<ProductDTO> getProduct(String id) {
    return productRepository.findById(id).map(ProductUtils::toDTO);
  }

  @Override
  public Mono<Page<ProductDTO>> getProductList(Integer page, Integer size, String productTypeCode) {
    Pageable pageable = getPageable(page, size, Sort.by(Direction.ASC, "title"));
    Flux<Product> resultList = switch (productTypeCode) {
      case CommonConstants.ALL -> productRepository.findAllBy(pageable);
      default -> productRepository.findAllByProductTypeCode(productTypeCode, pageable);
    };

    return resultList.map(ProductUtils::toDTO).collectList().zipWith(productRepository.count())
        .map(t -> new PageImpl<>(t.getT1(), pageable, t.getT2()));
  }
}

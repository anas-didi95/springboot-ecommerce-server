package com.anasdidi.ecommerce.service.producttype;

import org.springframework.data.domain.Page;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductTypeService {

  Mono<ProductTypeDTO> create(ProductTypeDTO dto, String logPrefix);

  Mono<ProductTypeDTO> update(ProductTypeDTO dto, String logPrefix);

  Mono<Page<ProductTypeDTO>> getProductTypeList(Integer page, Integer size);

  Flux<ProductTypeDTO> getProductTypeList();
}

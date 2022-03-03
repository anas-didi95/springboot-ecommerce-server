package com.anasdidi.ecommerce.service.product;

import org.springframework.data.domain.Page;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

  Mono<ProductDTO> create(ProductDTO dto, String logPrefix);

  Mono<ProductDTO> update(ProductDTO dto, String logPrefix);

  Flux<ProductDTO> getProductList();

  Mono<Page<ProductDTO>> getProductList(Integer page, Integer size, String productTypeCode);

  Mono<ProductDTO> getProduct(String id);
}

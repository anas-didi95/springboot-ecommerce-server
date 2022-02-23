package com.anasdidi.ecommerce.service.product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

  Mono<ProductDTO> create(ProductDTO dto, String logPrefix);

  Mono<ProductDTO> update(ProductDTO dto, String logPrefix);

  Flux<ProductDTO> getProductList();
}

package com.anasdidi.ecommerce.service.product;

import reactor.core.publisher.Mono;

interface ProductService {

  Mono<ProductDTO> create(ProductDTO dto, String logPrefix);
}

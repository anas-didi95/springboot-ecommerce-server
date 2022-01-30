package com.anasdidi.ecommerce.service.producttype;

import reactor.core.publisher.Mono;

interface ProductTypeService {

  Mono<ProductTypeDTO> create(ProductTypeDTO dto, String logPrefix);

  Mono<ProductTypeDTO> update(ProductTypeDTO dto, String logPrefix);
}

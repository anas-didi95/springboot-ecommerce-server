package com.anasdidi.ecommerce.service.producttype;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
interface ProductTypeRepository extends ReactiveCrudRepository<ProductType, String> {

  Mono<Boolean> existsByCode(String code);
}

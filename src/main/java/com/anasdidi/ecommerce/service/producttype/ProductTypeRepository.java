package com.anasdidi.ecommerce.service.producttype;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
interface ProductTypeRepository extends ReactiveSortingRepository<ProductType, String> {

  Mono<Boolean> existsByCode(String code);

  Mono<ProductType> findByCode(String code);

  Flux<ProductType> findAllBy(Pageable pageable);
}

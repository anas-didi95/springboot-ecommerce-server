package com.anasdidi.ecommerce.service.product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
interface ProductRepository extends ReactiveSortingRepository<Product, String> {

  Flux<Product> findAllByOrderByTitleAsc();

  Flux<Product> findAllBy(Pageable pageable);
}

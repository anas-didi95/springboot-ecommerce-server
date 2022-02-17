package com.anasdidi.ecommerce.service.product;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepository extends ReactiveSortingRepository<Product, String> {
}

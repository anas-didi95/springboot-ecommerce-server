package com.anasdidi.ecommerce.service.producttype;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductTypeRepository extends ReactiveCrudRepository<ProductType, String> {
}

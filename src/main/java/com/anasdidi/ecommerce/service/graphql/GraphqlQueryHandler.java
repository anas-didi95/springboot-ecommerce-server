package com.anasdidi.ecommerce.service.graphql;

import java.util.List;

import com.anasdidi.ecommerce.service.producttype.ProductTypeDTO;
import com.anasdidi.ecommerce.service.producttype.ProductTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import reactor.core.publisher.Mono;

@Component
class GraphqlQueryHandler implements GraphQLQueryResolver {

  private final ProductTypeService productTypeService;

  @Autowired
  GraphqlQueryHandler(ProductTypeService ProductTypeService) {
    this.productTypeService = ProductTypeService;
  }

  Mono<String> hello() {
    return Mono.just("Hello world");
  }

  Mono<List<ProductTypeDTO>> getProductTypeList() {
    return productTypeService.getProductTypeList().collectList();
  }
}

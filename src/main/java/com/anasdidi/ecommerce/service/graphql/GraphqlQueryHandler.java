package com.anasdidi.ecommerce.service.graphql;

import java.util.List;

import com.anasdidi.ecommerce.common.PaginationDTO;
import com.anasdidi.ecommerce.service.producttype.ProductTypeDTO;
import com.anasdidi.ecommerce.service.producttype.ProductTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import reactor.core.publisher.Mono;

@Component
class GraphqlQueryHandler implements GraphQLQueryResolver {

  private final ProductTypeService productTypeService;

  @Autowired
  GraphqlQueryHandler(ProductTypeService ProductTypeService) {
    this.productTypeService = ProductTypeService;
  }

  Mono<PaginationDTO> getPagination(Integer page, Integer size, DataFetchingEnvironment env) {
    System.out.println("pagination");
    Mono<List<Object>> resultList = env.getGraphQlContext().get("RESULT_SET");
    return resultList.map(r -> PaginationDTO.builder().pageNumber(page).pageSize(size).totalElements(r.size())
        .totalPages(r.size()).build());
  }

  Mono<List<ProductTypeDTO>> getProductTypeList(DataFetchingEnvironment env) {
    System.out.println("productTypeList");
    Mono<List<ProductTypeDTO>> resultList = productTypeService.getProductTypeList().collectList();
    env.getGraphQlContext().put("RESULT_SET", resultList);
    return resultList;
  }
}

package com.anasdidi.ecommerce.service.graphql;

import java.util.List;

import com.anasdidi.ecommerce.common.PaginationDTO;
import com.anasdidi.ecommerce.service.graphql.GraphqlConstants.Context;
import com.anasdidi.ecommerce.service.product.ProductDTO;
import com.anasdidi.ecommerce.service.product.ProductService;
import com.anasdidi.ecommerce.service.producttype.ProductTypeDTO;
import com.anasdidi.ecommerce.service.producttype.ProductTypeService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import reactor.core.publisher.Mono;

@Component
class GraphqlQueryHandler implements GraphQLQueryResolver {

  private final ProductTypeService productTypeService;
  private final ProductService productService;

  @Autowired
  GraphqlQueryHandler(ProductTypeService ProductTypeService, ProductService productService) {
    this.productTypeService = ProductTypeService;
    this.productService = productService;
  }

  @SuppressWarnings("unchecked")
  Mono<PaginationDTO> getPagination(DataFetchingEnvironment env) {
    Mono<Page<Object>> pageResult = (Mono<Page<Object>>) getGraphqlContext(env, Context.PAGE_RESULT);
    return pageResult.map(r -> PaginationDTO.builder().pageNumber(r.getNumber() + 1).pageSize(r.getSize())
        .totalElements(r.getTotalElements()).totalPages(r.getTotalPages()).build());
  }

  Mono<List<ProductTypeDTO>> getProductTypePage(Integer page, Integer size, DataFetchingEnvironment env) {
    Mono<Page<ProductTypeDTO>> pageResult = productTypeService.getProductTypeList(page, size);
    setGraphqlContext(env, Context.PAGE_RESULT, pageResult);
    return pageResult.map(p -> p.getContent());
  }

  Mono<List<ProductTypeDTO>> getProductTypeList(DataFetchingEnvironment env) {
    Mono<List<ProductTypeDTO>> resultList = productTypeService.getProductTypeList().collectList();
    return resultList;
  }

  Mono<List<ProductDTO>> getProductList(String title, DataFetchingEnvironment env) {
    if (!StringUtils.isBlank(title)) {
      return productService.getProductList(title).collectList();
    }
    return productService.getProductList().collectList();
  }

  Mono<ProductDTO> getProduct(String id, DataFetchingEnvironment env) {
    return productService.getProduct(id);
  }

  Mono<List<ProductDTO>> getProductPage(Integer page, Integer size, String productTypeCode,
      DataFetchingEnvironment env) {
    Mono<Page<ProductDTO>> resultPage = productService.getProductList(page, size, productTypeCode);
    setGraphqlContext(env, Context.PAGE_RESULT, resultPage);
    return resultPage.map(r -> r.getContent());
  }

  private void setGraphqlContext(DataFetchingEnvironment env, Context context, Object value) {
    env.getGraphQlContext().put(context.key, value);
  }

  private Object getGraphqlContext(DataFetchingEnvironment env, Context context) {
    return env.getGraphQlContext().get(context.key);
  }
}

package com.anasdidi.ecommerce.service.graphql;

import java.util.concurrent.CompletableFuture;

import com.anasdidi.ecommerce.service.product.ProductDTO;
import com.anasdidi.ecommerce.service.producttype.ProductTypeDTO;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
class GraphqlProductHandler implements GraphQLResolver<ProductDTO> {

  public CompletableFuture<ProductTypeDTO> getProductType(ProductDTO source, DataFetchingEnvironment env) {
    return env.<String, ProductTypeDTO>getDataLoader("PRODUcT_TYPE_LIST")
        .load(source.getProductTypeCode());
  }
}

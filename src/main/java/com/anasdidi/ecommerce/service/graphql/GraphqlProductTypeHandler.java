package com.anasdidi.ecommerce.service.graphql;

import com.anasdidi.ecommerce.service.producttype.ProductTypeDTO;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
class GraphqlProductTypeHandler implements GraphQLResolver<ProductTypeDTO> {

  public String getLastModifiedDate(ProductTypeDTO source, String format, DataFetchingEnvironment env) {
    return GraphqlUtils.getFormattedDate(source.getLastModifiedDate(), format);
  }
}

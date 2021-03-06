package com.anasdidi.ecommerce.service.graphql;

import com.anasdidi.ecommerce.service.graphql.GraphqlConstants.DataLoader;
import com.anasdidi.ecommerce.service.producttype.ProductTypeDTO;
import com.anasdidi.ecommerce.service.producttype.ProductTypeService;

import org.dataloader.DataLoaderFactory;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.server.ServerWebExchange;

import graphql.kickstart.spring.GraphQLSpringContext;
import graphql.kickstart.spring.GraphQLSpringServerWebExchangeContext;
import graphql.kickstart.spring.webflux.DefaultGraphQLSpringWebSocketSessionContext;
import graphql.kickstart.spring.webflux.GraphQLSpringWebSocketSessionContext;
import graphql.kickstart.spring.webflux.GraphQLSpringWebfluxContextBuilder;

@Component
class GraphqlContext implements GraphQLSpringWebfluxContextBuilder {

  private final ProductTypeService productTypeService;

  GraphqlContext(ProductTypeService productTypeService) {
    this.productTypeService = productTypeService;
  }

  @Override
  public GraphQLSpringContext build(ServerWebExchange serverWebExchange) {
    return new GraphQLSpringServerWebExchangeContext(buildDataLoaderRegistry(), serverWebExchange);
  }

  @Override
  public GraphQLSpringWebSocketSessionContext build(WebSocketSession webSocketSession) {
    return new DefaultGraphQLSpringWebSocketSessionContext(buildDataLoaderRegistry(), webSocketSession);
  }

  private DataLoaderRegistry buildDataLoaderRegistry() {
    DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();
    dataLoaderRegistry.register(DataLoader.PRODUCT_TYPE_LIST.key,
        DataLoaderFactory.<String, ProductTypeDTO>newMappedDataLoader(list -> productTypeService
            .getProductTypeList(list).collectMap(o -> o.getCode()).toFuture().minimalCompletionStage()));
    return dataLoaderRegistry;
  }
}

package com.anasdidi.ecommerce.config;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.server.ServerWebExchange;

import graphql.kickstart.spring.GraphQLSpringContext;
import graphql.kickstart.spring.GraphQLSpringServerWebExchangeContext;
import graphql.kickstart.spring.webflux.DefaultGraphQLSpringWebSocketSessionContext;
import graphql.kickstart.spring.webflux.GraphQLSpringWebSocketSessionContext;
import graphql.kickstart.spring.webflux.GraphQLSpringWebfluxContextBuilder;

@Component
public class GraphqlContext implements GraphQLSpringWebfluxContextBuilder {

  @Override
  public GraphQLSpringContext build(ServerWebExchange serverWebExchange) {
    return new GraphQLSpringServerWebExchangeContext(null, serverWebExchange);
  }

  @Override
  public GraphQLSpringWebSocketSessionContext build(WebSocketSession webSocketSession) {
    return new DefaultGraphQLSpringWebSocketSessionContext(null, webSocketSession);
  }
}

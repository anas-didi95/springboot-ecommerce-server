package com.anasdidi.ecommerce.service.graphql;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import reactor.core.publisher.Mono;

@Component
class GraphqlQueryHandler implements GraphQLQueryResolver {

  Mono<String> hello() {
    return Mono.just("Hello world");
  }
}

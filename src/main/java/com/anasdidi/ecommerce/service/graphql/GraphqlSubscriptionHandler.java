package com.anasdidi.ecommerce.service.graphql;

import java.time.Duration;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import reactor.core.publisher.Flux;

@Component
class GraphqlSubscriptionHandler implements GraphQLSubscriptionResolver {

  Publisher<Integer> hello() {
    return Flux.range(0, 100).delayElements(Duration.ofSeconds(1));
  }
}

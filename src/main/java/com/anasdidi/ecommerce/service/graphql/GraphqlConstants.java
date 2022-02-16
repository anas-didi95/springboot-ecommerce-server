package com.anasdidi.ecommerce.service.graphql;

final class GraphqlConstants {

  enum Context {
    PAGE_RESULT("1_ctx_pg_rst");

    final String key;

    Context(String key) {
      this.key = key;
    }
  }
}

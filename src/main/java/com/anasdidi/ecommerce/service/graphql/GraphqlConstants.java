package com.anasdidi.ecommerce.service.graphql;

final class GraphqlConstants {

  enum Context {
    PAGE_RESULT("1_ctx_pg_rst");

    final String key;

    Context(String key) {
      this.key = key;
    }
  }

  enum DataLoader {
    PRODUCT_TYPE_LIST("product_type_list");

    final String key;

    DataLoader(String key) {
      this.key = key;
    }
  }
}

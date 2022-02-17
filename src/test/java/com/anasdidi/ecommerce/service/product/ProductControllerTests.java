package com.anasdidi.ecommerce.service.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProductControllerTests {

  private final String baseUri = "/v1/product";
  private final WebTestClient webTestClient;

  @Autowired
  ProductControllerTests(WebTestClient webTestClient) {
    this.webTestClient = webTestClient;
  }

  @Test
  void testProductCreateSuccess() {
    webTestClient.post().uri(baseUri).accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
        .isEqualTo(HttpStatus.CREATED);
  }
}

package com.anasdidi.ecommerce.service.producttype;

import com.anasdidi.ecommerce.common.ResponseDTO;

import org.junit.jupiter.api.Assertions;
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
class ProductTypeControllerTests {

  private static final String BASE_URI = "/v1/producttype";
  private final WebTestClient webTestClient;

  @Autowired
  ProductTypeControllerTests(WebTestClient webTestClient) {
    this.webTestClient = webTestClient;
  }

  @Test
  void testProductTypeCreateSuccess() {
    webTestClient.get().uri(BASE_URI).accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
        .isEqualTo(HttpStatus.OK).expectBody(ResponseDTO.class).value(responseBody -> {
          Assertions.assertNotNull(responseBody.getId());
        });
  }
}

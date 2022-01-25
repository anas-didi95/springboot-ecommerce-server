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
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProductTypeControllerTests {

  private static final String BASE_URI = "/v1/producttype";
  private final WebTestClient webTestClient;
  private final ProductTypeRepository productTypeRepository;

  @Autowired
  ProductTypeControllerTests(WebTestClient webTestClient, ProductTypeRepository productTypeRepository) {
    this.webTestClient = webTestClient;
    this.productTypeRepository = productTypeRepository;
  }

  @Test
  void testProductTypeCreateSuccess() {
    String value = "TEST" + System.currentTimeMillis();
    ProductTypeDTO requestBody = ProductTypeDTO.builder().code(value).description(value).build();
    Long beforeCount = productTypeRepository.count().block();

    webTestClient.post().uri(BASE_URI).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(requestBody))
        .exchange().expectStatus()
        .isEqualTo(HttpStatus.CREATED).expectBody(ResponseDTO.class).value(responseBody -> {
          Assertions.assertNotNull(responseBody.getCode());
          Assertions.assertEquals(beforeCount + 1, productTypeRepository.count().block());
        });
  }
}

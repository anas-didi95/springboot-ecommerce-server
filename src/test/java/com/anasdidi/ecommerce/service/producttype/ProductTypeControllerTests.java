package com.anasdidi.ecommerce.service.producttype;

import com.anasdidi.ecommerce.common.ResponseDTO;
import com.anasdidi.ecommerce.common.TestUtils;

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

  private final String baseUri = "/v1/producttype";
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

    webTestClient.post().uri(baseUri).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(requestBody))
        .exchange().expectStatus()
        .isEqualTo(HttpStatus.CREATED).expectBody(ResponseDTO.class).value(responseBody -> {
          Assertions.assertNotNull(responseBody.getCode());
          Assertions.assertEquals(beforeCount + 1, productTypeRepository.count().block());
        });
  }

  @Test
  void testProductTypeValidationError() {
    ProductTypeDTO requestBody = ProductTypeDTO.builder().build();

    webTestClient.post().uri(baseUri).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(requestBody)).exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
        .expectBody(ResponseDTO.class).value(TestUtils::assertValidationError);
  }

  @Test
  void testProductTypeRecordAlreadyExistsError() {
    String value = "TEST" + System.currentTimeMillis();
    ProductTypeDTO requestBody = ProductTypeDTO.builder().code(value).description(value).build();
    ProductType domain = ProductType.builder().code(requestBody.getCode()).description(requestBody.getDescription())
        .build();

    productTypeRepository.save(domain).block();
    webTestClient.post().uri(baseUri).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(requestBody)).exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
        .expectBody(ResponseDTO.class).value(responseBody -> TestUtils.assertRecordNotFoundError(responseBody, value));
  }
}

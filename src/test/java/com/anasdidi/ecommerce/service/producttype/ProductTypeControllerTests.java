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

    webTestClient.post().uri(baseUri).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(requestBody))
        .exchange().expectStatus()
        .isEqualTo(HttpStatus.CREATED).expectBody(ResponseDTO.class).value(responseBody -> {
          Assertions.assertNotNull(responseBody.getCode());

          ProductType domain = productTypeRepository.findByCode(responseBody.getCode()).block();
          Assertions.assertEquals(value, domain.getDescription());
          Assertions.assertEquals(false, domain.getIsDeleted());
          Assertions.assertEquals(0, domain.getVersion());
        });
  }

  @Test
  void testProductTypeCreateValidationError() {
    ProductTypeDTO requestBody = ProductTypeDTO.builder().build();

    webTestClient.post().uri(baseUri).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(requestBody)).exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
        .expectBody(ResponseDTO.class).value(TestUtils::assertValidationError);
  }

  @Test
  void testProductTypeCreateRecordAlreadyExistsError() {
    String value = "TEST" + System.currentTimeMillis();
    ProductTypeDTO requestBody = ProductTypeDTO.builder().code(value).description(value).build();
    ProductType domain = ProductType.builder().code(requestBody.getCode()).description(requestBody.getDescription())
        .build();

    productTypeRepository.save(domain).block();
    webTestClient.post().uri(baseUri).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(requestBody)).exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
        .expectBody(ResponseDTO.class)
        .value(responseBody -> TestUtils.assertRecordAlreadyExistsError(responseBody, value));
  }

  @Test
  void testProductTypeUpdateSuccess() {
    String value = "TEST" + System.currentTimeMillis();
    ProductTypeDTO requestBody = ProductTypeDTO.builder().code(value).description(value).build();
    ProductType domain = ProductType.builder().code(requestBody.getCode()).description(requestBody.getDescription())
        .build();

    productTypeRepository.save(domain).block();
    String newValue = "NEW" + System.currentTimeMillis();
    ProductTypeDTO newRequestBody = ProductTypeDTO.builder().code(domain.getCode()).description(newValue)
        .isDeleted(true)
        .version(domain.getVersion()).build();

    webTestClient.put().uri(baseUri + "/" + domain.getCode()).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(newRequestBody)).exchange().expectStatus().isEqualTo(HttpStatus.OK)
        .expectBody(ResponseDTO.class).value(responseBody -> {
          Assertions.assertEquals(value, responseBody.getCode());

          ProductType newDomain = productTypeRepository.findByCode(domain.getCode()).block();
          Assertions.assertEquals(domain.getVersion() + 1, newDomain.getVersion());
          Assertions.assertEquals(newValue, newDomain.getDescription());
          Assertions.assertEquals(true, newDomain.getIsDeleted());
        });
  }

  @Test
  void testProductUpdateValidationError() {
    String value = "TEST" + System.currentTimeMillis();
    ProductTypeDTO requestBody = ProductTypeDTO.builder().code(value).description(value).build();
    ProductType domain = ProductType.builder().code(requestBody.getCode()).description(requestBody.getDescription())
        .build();

    productTypeRepository.save(domain).block();
    ProductTypeDTO newRequestBody = ProductTypeDTO.builder().build();

    webTestClient.put().uri(baseUri + "/" + domain.getCode()).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(newRequestBody)).exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
        .expectBody(ResponseDTO.class).value(TestUtils::assertValidationError);
  }

  @Test
  void testProductTypeUpdateRecordNotFoundError() {
    String value = "TEST" + System.currentTimeMillis();
    ProductTypeDTO requestBody = ProductTypeDTO.builder().code(value).description(value).build();
    ProductType domain = ProductType.builder().code(requestBody.getCode()).description(requestBody.getDescription())
        .build();

    String newValue = "NEW" + System.currentTimeMillis();
    ProductTypeDTO newRequestBody = ProductTypeDTO.builder().code(domain.getCode()).description(newValue)
        .isDeleted(true)
        .version(0L).build();
    webTestClient.put().uri(baseUri + "/" + domain.getCode()).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(newRequestBody)).exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
        .expectBody(ResponseDTO.class)
        .value(responseBody -> TestUtils.assertRecordNotFoundError(responseBody, domain.getCode()));
  }

  @Test
  void testProductTypeUpdateVersionNotMatchedError() {
    String value = "TEST" + System.currentTimeMillis();
    ProductTypeDTO requestBody = ProductTypeDTO.builder().code(value).description(value).build();
    ProductType domain = ProductType.builder().code(requestBody.getCode()).description(requestBody.getDescription())
        .build();

    productTypeRepository.save(domain).block();
    String newValue = "NEW" + System.currentTimeMillis();
    Long wrongVersion = -1L;
    ProductTypeDTO newRequestBody = ProductTypeDTO.builder().code(domain.getCode()).description(newValue)
        .isDeleted(true)
        .version(wrongVersion).build();

    webTestClient.put().uri(baseUri + "/" + domain.getCode()).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(newRequestBody)).exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
        .expectBody(ResponseDTO.class)
        .value(responseBody -> TestUtils.assertVersionNotMatchedError(responseBody, domain.getVersion(), wrongVersion));
  }
}

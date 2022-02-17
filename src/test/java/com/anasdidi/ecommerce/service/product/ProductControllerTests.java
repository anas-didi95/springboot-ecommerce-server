package com.anasdidi.ecommerce.service.product;

import java.math.BigDecimal;

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
class ProductControllerTests {

  private final String baseUri = "/v1/product";
  private final WebTestClient webTestClient;
  private final ProductRepository productRepository;

  @Autowired
  ProductControllerTests(WebTestClient webTestClient, ProductRepository productRepository) {
    this.webTestClient = webTestClient;
    this.productRepository = productRepository;
  }

  @Test
  void testProductCreateSuccess() {
    String value = "TEST" + System.currentTimeMillis();
    BigDecimal value2 = BigDecimal.ZERO;
    String productTypeCode = "MENS";
    ProductDTO requestBody = ProductDTO.builder().title(value).description(value).price(value2)
        .productTypeCode(productTypeCode).build();

    webTestClient.post().uri(baseUri).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(requestBody))
        .exchange().expectStatus()
        .isEqualTo(HttpStatus.CREATED).expectBody(ResponseDTO.class).value(responseBody -> {
          Assertions.assertNotNull(responseBody.getId());

          Product domain = productRepository.findById(responseBody.getId()).block();
          Assertions.assertEquals(value, domain.getTitle());
          Assertions.assertEquals(value, domain.getDescription());
          Assertions.assertEquals(productTypeCode, domain.getProductTypeCode());
          Assertions.assertTrue(value2.compareTo(domain.getPrice()) == 0);
          Assertions.assertEquals(false, domain.getIsDeleted());
          Assertions.assertEquals(0, domain.getVersion());
        });
  }

  @Test
  void testProductCreateValidationError() {
    ProductDTO requestBody = ProductDTO.builder().build();

    webTestClient.post().uri(baseUri).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(requestBody))
        .exchange().expectStatus()
        .isEqualTo(HttpStatus.BAD_REQUEST).expectBody(ResponseDTO.class).value(TestUtils::assertValidationError);
  }
}

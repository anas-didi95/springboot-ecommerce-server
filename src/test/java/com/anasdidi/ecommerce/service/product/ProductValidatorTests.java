package com.anasdidi.ecommerce.service.product;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProductValidatorTests {

  private final ProductValidator productValidator;

  @Autowired
  ProductValidatorTests(ProductValidator productValidator) {
    this.productValidator = productValidator;
  }

  @Test
  void testValidateCreate() {
    ProductDTO dto = ProductDTO.builder().build();
    List<String> actualList = productValidator.validateCreate(dto);
    List<String> expectedList = Arrays.asList("[title] is mandatory field!", "[price] is mandatory field!",
        "[productTypeCode] is mandatory field!", "[description] is mandatory field!");

    Assertions.assertEquals(expectedList.size(), actualList.size());
    expectedList.stream().forEach(
        expected -> Assertions.assertTrue(actualList.contains(expected), "Message [" + expected + "] not found!"));
  }
}

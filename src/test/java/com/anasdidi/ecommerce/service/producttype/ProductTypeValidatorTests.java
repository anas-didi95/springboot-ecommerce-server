package com.anasdidi.ecommerce.service.producttype;

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
class ProductTypeValidatorTests {

  private final ProductTypeValidator productTypeValidator;

  @Autowired
  ProductTypeValidatorTests(ProductTypeValidator productTypeValidator) {
    this.productTypeValidator = productTypeValidator;
  }

  @Test
  void testValidateCreate() {
    ProductTypeDTO dto = ProductTypeDTO.builder().build();
    List<String> actualList = productTypeValidator.validateCreate(dto);
    List<String> expectedList = Arrays.asList("[code] is mandatory field!", "[description] is mandatory field!");

    Assertions.assertEquals(expectedList.size(), actualList.size());
    expectedList.stream().forEach(
        expected -> Assertions.assertTrue(actualList.contains(expected), "Message [" + expected + "] not found!"));
  }

  @Test
  void testValidateUpdate() {
    ProductTypeDTO dto = ProductTypeDTO.builder().build();
    List<String> actualList = productTypeValidator.validateUpdate(dto);
    List<String> expectedList = Arrays.asList("[description] is mandatory field!", "[isDeleted] is mandatory field!",
        "[version] is mandatory field!");

    Assertions.assertEquals(expectedList.size(), actualList.size());
    expectedList.stream().forEach(
        expected -> Assertions.assertTrue(actualList.contains(expected), "Message [" + expected + "] not found!"));
  }
}

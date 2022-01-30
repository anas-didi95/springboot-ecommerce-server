package com.anasdidi.ecommerce.common;

import org.junit.jupiter.api.Assertions;

public final class TestUtils {

  public static void assertValidationError(ResponseDTO responseBody) {
    String code = "E001";
    String message = "Validation error!";

    Assertions.assertTrue(!responseBody.getErrorList().isEmpty());
    assertError(responseBody, code, message);
  }

  public static void assertRecordNotFoundError(ResponseDTO responseBody, String value) {
    String code = "E002";
    String message = String.format("Record[%s] already exists!", value);

    assertError(responseBody, code, message);
  }

  private static void assertError(ResponseDTO responseBody, String code, String message) {
    Assertions.assertEquals(code, responseBody.getCode());
    Assertions.assertEquals(message, responseBody.getMessage());
    Assertions.assertNotNull(responseBody.getRequestId());
  }
}

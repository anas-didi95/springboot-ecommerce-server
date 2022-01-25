package com.anasdidi.ecommerce.common;

import org.junit.jupiter.api.Assertions;

public final class TestUtils {

  public static void assertValidationError(ResponseDTO responseBody) {
    String code = "E001";
    String message = "Validation error!";

    Assertions.assertEquals(code, responseBody.getCode());
    Assertions.assertEquals(message, responseBody.getMessage());
    Assertions.assertNotNull(responseBody.getRequestId());
    Assertions.assertTrue(!responseBody.getErrorList().isEmpty());
  }
}

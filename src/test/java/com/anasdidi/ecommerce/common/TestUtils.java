package com.anasdidi.ecommerce.common;

import org.junit.jupiter.api.Assertions;

public final class TestUtils {

  public static void assertValidationError(ResponseDTO responseBody) {
    String code = "E001";
    String message = "Validation error!";

    Assertions.assertTrue(!responseBody.getErrorList().isEmpty());
    assertError(responseBody, code, message);
  }

  public static void assertRecordAlreadyExistsError(ResponseDTO responseBody, String value) {
    String code = "E002";
    String message = String.format("Record[%s] already exists!", value);

    assertError(responseBody, code, message);
  }

  public static void assertRecordNotFoundError(ResponseDTO responseBody, String value) {
    String code = "E003";
    String message = String.format("Record[%s] not found!", value);

    assertError(responseBody, code, message);
  }

  public static void assertVersionNotMatchedError(ResponseDTO responseBody, Long expectedVersion, Long actualVersion) {
    String code = "E004";
    String message = String.format("Request version[%d] not matched with current version[%d]!", actualVersion,
        expectedVersion);

    assertError(responseBody, code, message);
  }

  private static void assertError(ResponseDTO responseBody, String code, String message) {
    Assertions.assertEquals(code, responseBody.getCode());
    Assertions.assertEquals(message, responseBody.getMessage());
    Assertions.assertNotNull(responseBody.getRequestId());
  }
}

package com.anasdidi.ecommerce.common;

public final class CommonConstants {

  public static final String ALL = "ALL";

  public enum Error {
    VALIDATION("E001"),
    RECORD_ALREADY_EXISTED("E002"),
    RECORD_NOT_FOUND("E003"),
    VERSION_NOT_MATCHED("E004"),
    DATA_INTEGRITY_VIOLATION("E005");

    public final String code;

    Error(String code) {
      this.code = code;
    }
  }
}

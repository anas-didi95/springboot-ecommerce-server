package com.anasdidi.ecommerce.common;

public final class CommonConstants {

  public enum Error {
    VALIDATION("E001"),
    RECORD_ALREADY_EXISTS("E002"),
    RECORD_NOT_FOUND("E003");

    public final String code;

    Error(String code) {
      this.code = code;
    }
  }
}

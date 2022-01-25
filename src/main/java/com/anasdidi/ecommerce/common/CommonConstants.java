package com.anasdidi.ecommerce.common;

public final class CommonConstants {

  public enum Error {
    VALIDATION("E001");

    public final String code;

    Error(String code) {
      this.code = code;
    }
  }
}

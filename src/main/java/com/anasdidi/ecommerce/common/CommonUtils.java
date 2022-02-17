package com.anasdidi.ecommerce.common;

import java.util.UUID;

public class CommonUtils {

  public static String generateId() {
    return UUID.randomUUID().toString();
  }
}

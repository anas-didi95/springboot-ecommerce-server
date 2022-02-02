package com.anasdidi.ecommerce.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class RecordNotFoundException extends Exception {

  private final String value;
}

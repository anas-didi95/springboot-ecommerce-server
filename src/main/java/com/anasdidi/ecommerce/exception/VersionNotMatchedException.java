package com.anasdidi.ecommerce.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class VersionNotMatchedException extends Exception {

  private final Long expectedVersion;
  private final Long actualVersion;
}

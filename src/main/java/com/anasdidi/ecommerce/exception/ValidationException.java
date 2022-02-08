package com.anasdidi.ecommerce.exception;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class ValidationException extends Exception {

  private final List<String> errorList;
}
